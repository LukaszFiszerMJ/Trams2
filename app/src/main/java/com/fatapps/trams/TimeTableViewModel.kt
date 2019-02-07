package com.fatapps.trams

import androidx.databinding.ObservableArrayMap
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.fatapps.domain.interactor.trams.GetTramTimeTableUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TimeTableViewModel (
    private val stopId: String,
    private val stopName: String,
    private val getTramTimeTableUseCase: GetTramTimeTableUseCase,
    val disposable: CompositeDisposable = CompositeDisposable()

): ViewModel() {
    val stopNameField = ObservableField<String>("")
    val hours = ObservableArrayMap<String, MutableList<String>>()



    init {
        stopNameField.set(stopName)

        val tramStopNr  = stopId.takeLast(2)
        val tramStopId = stopId.take(stopId.length - 2)

        disposable.add(
        getTramTimeTableUseCase.execute(tramStopId, tramStopNr)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                    it.map.forEach{ entry ->

                        entry.value.forEach {

                            if(hours[entry.key] == null)
                                hours[entry.key] =  mutableListOf()
                            hours[entry.key]!!.add(it)

                        }

                    }

            }, {})
)
    }
}