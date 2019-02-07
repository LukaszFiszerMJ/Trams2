package com.fatapps.data.trams

import com.fatapps.data.trams.converter.StopModelConverter
import com.fatapps.data.trams.converter.TimeTableModelConverter
import com.fatapps.data.trams.db.StopDao
import com.fatapps.data.trams.service.TimeTableRemoteService
import com.fatapps.data.trams.service.TramStopsRemoteService
import com.fatapps.data.trams.service.pojo.ResultApi
import com.fatapps.domain.model.Result
import com.fatapps.domain.model.Stop
import com.fatapps.domain.repo.TramsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class TramsRepositoryImpl(
    private val tramStopsRemoteService: TramStopsRemoteService,
    private val timeTableRemoteService: TimeTableRemoteService,
    private val stopModelConverter: StopModelConverter,
    private val timeTableModelConverter: TimeTableModelConverter,
    private val stopDao: StopDao

) : TramsRepository {

    private var memory: List<Stop>? = null

    override fun fetchStops(): Single<List<Stop>> {
        Timber.d("fetchStops()")
        return Observable.concat(memory(), disk().filter { it.isNotEmpty() }, network())
            .first(listOf())
    }

    private fun disk(): Observable<List<Stop>> {

        return stopDao.getStops().map {
            it.map {
                stopModelConverter.modelToDomain(it)
            }
        }.doOnNext { li ->
            Timber.d("Data from db ${li.size}")
        }
    }

    private fun memory(): Observable<List<Stop>> {
        val observable: Observable<List<Stop>> = Observable.create { subscriber ->
            memory?.let {
                subscriber.onNext(memory!!)
            }
            subscriber.onComplete()
        }
        return observable.doOnNext { _ ->
            Timber.d("Data from memory")
        }
    }

    private fun network(): Observable<List<Stop>> {

        return tramStopsRemoteService.getTramStops().map {
            it.stops.map { stopModelConverter.apiToDomain(it) }
        }.doOnNext { data ->
            Timber.d("Data from network saving disk and memory")
            stopDao.saveStops(data.map { stopModelConverter.domainToModel(it) }).subscribe()
        }
    }

    override fun getTramTimeTable(stopId: String, stopNumber: String): Single<Result> {
        return Observable.concat(

          diskTimeTable(stopId, stopNumber)

            .filter{
                Timber.d("filter out: ${it.map.isNotEmpty()}")
                it.map.isNotEmpty()}

        ,networkTimeTable(stopId, stopNumber))
            .firstOrError()
    }

    private fun diskTimeTable(stopId: String, stopNumber: String): Observable<Result> {
        return stopDao.getTimeTable(stopId, stopNumber).map {
            timeTableModelConverter.modelToDomain(it)
        }
    }

    private fun networkTimeTable(stopId: String, stopNumber: String) : Observable<Result>{
        return timeTableRemoteService
            .getTimeTable(busStopId = stopId, busstopNr = stopNumber)
            .map {
                timeTableModelConverter.apiToDomain(it.result)
            }

            .doOnNext{

                    result->
                Timber.d("Save to db ${result.map.size}")
                stopDao.saveTimeTable(timeTableModelConverter
                    .domainToModel(stopId, stopNumber, result)).subscribe()
            }
    }
}