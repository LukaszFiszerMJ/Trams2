package com.fatapps.data.trams.converter

import com.fatapps.data.trams.db.model.TimeTableElementModel
import com.fatapps.data.trams.service.pojo.ResultApi
import com.fatapps.data.trams.service.pojo.StopApi
import com.fatapps.domain.model.Result
import com.fatapps.domain.model.Stop

interface TimeTableModelConverter {

    fun apiToDomain(resultApi: List<ResultApi>): Result

    fun domainToModel(stopId: String, stopNumber: String, result: Result): List<TimeTableElementModel>

    fun modelToDomain(timeElems: List<TimeTableElementModel>): Result
}