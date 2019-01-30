package com.fatapps.domain.util

import com.fatapps.domain.model.TramLocation
import io.reactivex.Observable

interface LocationService {
    fun trackLocation() : Observable<TramLocation>
}