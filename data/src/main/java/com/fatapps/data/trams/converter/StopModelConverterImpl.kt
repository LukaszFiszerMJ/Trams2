package com.fatapps.data.trams.converter

import com.fatapps.data.trams.db.model.StopModel
import com.fatapps.data.trams.service.pojo.StopApi
import com.fatapps.domain.model.Stop



class StopModelConverterImpl : StopModelConverter {

    override fun domainToModel(stop: Stop): StopModel {
        val stopModel = StopModel()
        stopModel.stopId = stop.stopId
        stopModel.direction = stop.direction
        stopModel.lat  =  stop.lat
        stopModel.lon  =  stop.lon
        stopModel.line  =  stop.line
        stopModel.name  =  stop.name
        return stopModel
    }

    override fun modelToDomain(stopModel: StopModel): Stop {
    return Stop(direction = stopModel.direction,
            lat = stopModel.lat,
            lon = stopModel.lon,
            name = stopModel.name,
            stopId = stopModel.stopId,
            line = stopModel.line)

    }

    override fun apiToDomain(stopApi: StopApi): Stop {
        return Stop(direction = stopApi.direction,
                        lat = stopApi.lat,
                        lon = stopApi.lon,
                        name = stopApi.name,
                        stopId = stopApi.stopId,
                        line = stopApi.line
        )
    }
}