package com.fatapps.data.trams.converter

import com.fatapps.data.trams.db.model.TimeTableElementModel
import com.fatapps.data.trams.service.pojo.ResultApi
import com.fatapps.data.trams.service.pojo.ValueApi
import com.fatapps.domain.model.Result
import com.fatapps.domain.model.Value
import timber.log.Timber

class TimeTableModelConverterImpl :TimeTableModelConverter {

    override fun domainToModel(stopId: String, stopNumber: String, result: Result)
            : List<TimeTableElementModel> {

        return result.map.flatMap {
             it.value.map { minute->
                val time = TimeTableElementModel()
                time.id = stopId + stopNumber + it.key + minute
                time.stopId = stopId
                time.stopNumber = stopNumber
                time.hour = it.key
                time.minute = minute
                time
            }
        }
    }

    override fun modelToDomain(timeElems: List<TimeTableElementModel>)
            : Result {

        val result = Result()

        timeElems.forEach {
            if (result.map[it.hour] == null)
                result.map[it.hour] = mutableListOf()
            result.map[it.hour]!!.add(it.minute)
        }
        return result
    }

    override fun apiToDomain(resultApis: List<ResultApi>): Result {
        val result = Result()
        result.map = hashMapOf()
        resultApis.forEach {resultApi->
        resultApi.values.map {

        if (it.key == "czas") {

            val mapKey = it.value!!.take(2)
            val mapValue = it.value!!.split(":")[1]

            //Timber.d("mapKey $mapKey mapValue $mapValue")

            if (result.map[mapKey] == null)
                result.map[mapKey] = mutableListOf()
            result.map[mapKey]!!.add(mapValue)
        }

        }
    }
        return result
    }

}