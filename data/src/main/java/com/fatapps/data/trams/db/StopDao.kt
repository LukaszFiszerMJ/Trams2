package com.fatapps.data.trams.db

import com.fatapps.data.trams.db.model.StopModel
import com.fatapps.data.trams.db.model.TimeTableElementModel
import com.fatapps.domain.model.Result
import io.reactivex.Completable
import io.reactivex.Observable


interface StopDao {

    fun getStops(): Observable<List<StopModel>>

    fun saveStops(stops: List<StopModel>) : Completable

    fun getTimeTable(stopId: String, stopNumber: String) : Observable<List<TimeTableElementModel>>

    fun saveTimeTable(times: List<TimeTableElementModel>): Completable
}