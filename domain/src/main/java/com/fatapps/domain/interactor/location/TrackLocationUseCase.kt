package com.fatapps.domain.interactor.location

import com.fatapps.domain.model.TramLocation
import com.fatapps.domain.util.LocationService
import io.reactivex.Observable


class TrackLocationUseCase(
    private val locationService: LocationService)
{
    fun execute(): Observable<TramLocation> {
        return locationService.trackLocation()

    }
}