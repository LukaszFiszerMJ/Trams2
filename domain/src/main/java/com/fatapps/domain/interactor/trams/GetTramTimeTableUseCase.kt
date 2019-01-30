package com.fatapps.domain.interactor.trams

import com.fatapps.domain.model.Result
import com.fatapps.domain.model.Stop
import com.fatapps.domain.repo.TramsRepository
import io.reactivex.Single

class GetTramTimeTableUseCase(private val tramsRepo: TramsRepository) {

    fun execute(stopId: String,stopNumber: String ) : Single<Result> {
        return tramsRepo.getTramTimeTable(
            stopId, stopNumber
        )
    }
}