package com.fatapps.data.trams.db

import com.fatapps.data.trams.db.model.StopModel
import com.fatapps.data.trams.db.model.TimeTableElementModel
import com.fatapps.domain.model.Result
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import timber.log.Timber

class StopDaoImpl : StopDao {

    override fun getStops(): Observable<List<StopModel>> {

    return Observable.create<List<StopModel>>{
        emitter->
        Realm.getDefaultInstance()
                .executeTransaction { realm ->
                    val list: List<StopModel> = realm.where(StopModel::class.java).findAll()
                    Timber.d("Lists $list")
                    emitter.onNext(list)
                    emitter.onComplete()
                }
    } }

    override fun saveStops(stops: List<StopModel>): Completable {

        return Completable.fromAction {
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                Timber.d("call is saved to realm")
                realm.insert(stops)
            }
        }
    }

    override fun getTimeTable(stopId: String, stopNumber: String)
            : Observable<List<TimeTableElementModel>> {
        return Observable.create<List<TimeTableElementModel>>{
            emitter ->
                    val list: List<TimeTableElementModel> = Realm.getDefaultInstance()
                        .where(TimeTableElementModel::class.java)
                        .equalTo("stopId", stopId)
                        .equalTo("stopNumber", stopNumber)
                        .findAll()

                    Timber.d("Find for stopId: $stopId stopNumber: $stopNumber size: ${list.size}")
                    emitter.onNext(list)
                    emitter.onComplete()
        }
    }

    override fun saveTimeTable(times: List<TimeTableElementModel>)
            : Completable {
        return Completable.fromAction {
            val realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                Timber.d("times is saved to realm")
                realm.insert(times)
            }
            realm.close()
        }

    }
}