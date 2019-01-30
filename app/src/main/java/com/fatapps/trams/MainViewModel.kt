package com.fatapps.trams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fatapps.domain.interactor.location.TrackLocationUseCase
import com.fatapps.domain.interactor.trams.FetchTramStopsUseCase
import com.fatapps.domain.interactor.trams.GetTramTimeTableUseCase
import com.fatapps.domain.model.Stop
import com.fatapps.domain.model.TramLocation
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*




class MainViewModel(
        fetchTramUseCase: FetchTramStopsUseCase,
        trackLocationUseCase: TrackLocationUseCase,
        getTramTimeTableUseCase: GetTramTimeTableUseCase
) : ViewModel() {

    private var liveStops = MutableLiveData<List<Stop>>()
    private var liveLocation = MutableLiveData<TramLocation>()

    private val disposables = CompositeDisposable()
    init {

            fetchTramUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                liveStops.postValue(list)
            },
            { t -> Timber.e("${t.message}") }
            ).addToComposite(disposables)


//        fetchTramUseCase.execute().toObservable()
//            .subscribeOn(Schedulers.io())
//         .map{
//
//                it.forEach {
//
//                    Timber.d("Przystanek ${it.name}")
//                }
//                liveStops.postValue(it)
//                it
//            }
//
//            .flatMapIterable{it}.flatMapSingle { stop->
//            val tramStopNr  = stop.stopId.takeLast(2)
//            val tramStopId = stop.stopId.take(stop.stopId.length - 2)
//
//            getTramTimeTableUseCase.execute(tramStopId, tramStopNr)
//                .map{
//                Pair(it, stop)
//            }
//        }
//            .map{
//            val stop = Sstop()
//            val listy : MutableList<Int> = mutableListOf()
//                it.first.map.map{entry->
//
//               entry.value.forEach {
//                   listy.add( (entry.key+ it).toInt())
//                }
//
//            }
//                listy.sort()
//            val tramLoc = TramLocation()
//            tramLoc.lon = it.second.lon
//            tramLoc.lat = it.second.lat
//            stop.location = tramLoc
//            stop.name = it.second.name
//
//            stop.lista = listy
//
//            stop
//                stop
//
//        }.toList()
//
//            .subscribe({
//
//                Timber.d("FFFF")
//                calculateRedDotLocation(0, it)
//            },{})



//        val tramStopNr  = stopId.takeLast(2)
//        val tramStopId = stopId.take(stopId.length - 2)
//
//        disposable.add(
//            getTramTimeTableUseCase.execute(tramStopId, tramStopNr)




      //  trackLocationUseCase.execute()
       //     .subscribe{
        //        Timber.d("Lat: ${it.lat} Lon: ${it.lon}")


                //find nearest tram

//                val currentTime = Calendar.getInstance().time
//                get cur time

                // found = find biggest smaller or equal{if equal break}

                //go down

                //loop find bigger than found and smaller or equal {if equal or no value break}

//java date

       //     }

    }



    fun getTramStops(): LiveData<List<Stop>> {
        return liveStops
    }

    fun trackLocation(): LiveData<TramLocation> {
        return liveLocation
    }
    //lista ulozona tak ze im nizej tym blizej do stacji koncowej
    //lista zawiera daty odjazdow tramwajow na kazdym przystanku
    class Sstop{

     var location: TramLocation = TramLocation()
     var name : String =""
      var lista : List<Int> = listOf()

     }
    private fun calculateRedDotLocation(nearest: Int, list: List<Sstop>){

        val currentTime = Calendar.getInstance()
        currentTime.timeZone = TimeZone.getDefault()

        val hr = (currentTime.time.hours +1).toString()
        val min = String.format("%02d",currentTime.time.minutes)


        val myTime = (hr+min).toInt()

        Timber.d("MYTIME MYTIME $myTime")

        Timber.d("My time $myTime")

        val reversedList = list.reversed()

        var nr= nearest

        //nr++
Timber.d("BUS STOP nr $nr name ${reversedList[nr].name}")
        var lastStop = -1
        var lastTime = -1
        var prevTime = -1
        var goDeeper = false

        var biggest = findBiggestSmaller(myTime, reversedList[nr].lista)

        lastStop = nr
        prevTime = biggest
        lastTime = biggest

        if(biggest != myTime) {
            goDeeper = true
        }
        while(goDeeper){
            nr++
            lastTime = findFirstBigger(lastTime, reversedList[nr].lista)
            Timber.d("BUS STOP nr $nr name ${reversedList[nr].name} time: $lastTime")

            if(lastTime >= myTime) {
                break
            }
            prevTime = lastTime
        }
        Timber.d("RESULT prevTime: $prevTime lastTime $lastTime stop ${reversedList[nr].name} number $nr")
    }
     private fun findBiggestSmaller(ref :Int, ser: List<Int>) :Int{

         var ret = 0
         var x = ser.size-1
         while (x >= 0) {

             if(ser[x] <  ref) {
                 ret = ser[x]
                 break
             }
             x--
         }
         return ret
    }

    private fun findFirstBigger( ref: Int , ser: List<Int>): Int{

        var firstBigger = -1

        for (item in ser) {
            if(item > ref ) {
                firstBigger = item
                break
            }
    }
        return firstBigger
    }

    override fun onCleared() {
        Timber.d("onCleared")
        super.onCleared()
        disposables.clear()
    }

}