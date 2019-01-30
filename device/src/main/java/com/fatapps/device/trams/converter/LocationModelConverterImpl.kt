package com.fatapps.device.trams.converter

import android.location.Location
import com.fatapps.domain.model.TramLocation

class LocationModelConverterImpl : LocationModelConverter {

    override fun apiToDomain(location: Location): TramLocation{
        val tramLocation = TramLocation()

        tramLocation.lat = location.latitude
        tramLocation.lon = location.longitude

        return tramLocation
    }
}