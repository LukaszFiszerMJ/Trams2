package com.fatapps.data.trams.converter

import com.fatapps.data.trams.db.model.StopModel
import com.fatapps.data.trams.service.pojo.StopApi
import com.fatapps.domain.model.Stop

interface StopModelConverter {

    fun apiToDomain(stopApi: StopApi): Stop

    fun domainToModel(stop: Stop): StopModel

    fun modelToDomain(stopModel: StopModel): Stop
}