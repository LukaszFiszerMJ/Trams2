package com.fatapps.domain.repo

import com.fatapps.domain.model.Result
import com.fatapps.domain.model.Stop
import io.reactivex.Single

interface TramsRepository {

    fun fetchStops(): Single<List<Stop>>

    fun getTramTimeTable(
        stopId: String,
        stopNumber: String
    ): Single<Result>
}