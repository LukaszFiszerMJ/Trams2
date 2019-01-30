package com.fatapps.device.trams.converter

import android.location.Location
import com.fatapps.domain.model.TramLocation

interface LocationModelConverter {

    fun apiToDomain(location: Location): TramLocation
}