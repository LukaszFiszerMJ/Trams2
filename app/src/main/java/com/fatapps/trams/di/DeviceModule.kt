package com.fatapps.trams.di

import android.content.Context
import android.location.LocationManager
import com.fatapps.device.trams.LocationManagerWrapper
import com.fatapps.device.trams.converter.LocationModelConverter
import com.fatapps.device.trams.converter.LocationModelConverterImpl
import com.fatapps.domain.util.LocationService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val DeviceModule = module {
    val context by lazy { androidApplication() }

    single<LocationManager> { context.getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    single<LocationModelConverter>{LocationModelConverterImpl()}
    single { LocationManagerWrapper(get(), get()) }

}