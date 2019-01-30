package com.fatapps.domain.interactor.trams

import com.fatapps.domain.model.Stop
import com.fatapps.domain.repo.TramsRepository
import io.reactivex.Single

class FetchTramStopsUseCase(private val tramsRepo: TramsRepository) {

    fun execute() : Single<List<Stop>> {
        return tramsRepo.fetchStops()
    }
}