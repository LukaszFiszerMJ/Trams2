package com.fatapps.device.trams


import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import com.fatapps.device.trams.converter.LocationModelConverter
import com.fatapps.domain.model.TramLocation
import com.fatapps.domain.util.LocationService
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber


open class LocationManagerWrapper(
    private val locationManager: LocationManager,
private val locationModelConverter: LocationModelConverter
) : LocationService {

    val trackLocationSubject = BehaviorSubject.create<TramLocation>()

    init {
        initLocationTracker()
    }

    @SuppressLint("MissingPermission")
    private fun initLocationTracker(){

        val lastLocation  = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        trackLocationSubject.onNext(
            locationModelConverter.apiToDomain(lastLocation)
        )
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0L,0F,
            object:LocationListener{
                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                    Timber.d("Not supported")
                }

                override fun onProviderEnabled(p0: String?) {
                    Timber.d("Not supported")
                }

                override fun onProviderDisabled(p0: String?) {
                    Timber.d("Not supported")
                }

                override fun onLocationChanged(p0: Location) {
                    trackLocationSubject.onNext(locationModelConverter.apiToDomain(p0))
                }

            })
    }
    override fun trackLocation(): Observable<com.fatapps.domain.model.TramLocation> {
        return trackLocationSubject
    }

}





