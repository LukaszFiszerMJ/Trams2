package com.fatapps.trams.di

import com.fatapps.data.trams.TramsRepositoryImpl
import com.fatapps.data.trams.converter.StopModelConverter
import com.fatapps.data.trams.converter.StopModelConverterImpl
import com.fatapps.data.trams.converter.TimeTableModelConverter
import com.fatapps.data.trams.converter.TimeTableModelConverterImpl
import com.fatapps.data.trams.db.StopDao
import com.fatapps.data.trams.db.StopDaoImpl
import com.fatapps.device.trams.LocationManagerWrapper
import com.fatapps.domain.repo.TramsRepository
import com.fatapps.domain.util.LocationService
import org.koin.dsl.module.module

val DataModule = module {

    single <StopModelConverter> { StopModelConverterImpl() }
    single<TimeTableModelConverter>{ TimeTableModelConverterImpl() }
    single<TramsRepository> { TramsRepositoryImpl(get(), get(), get(), get(), get())}
    single <LocationService>{ LocationManagerWrapper(get(), get()) }

    single<StopDao>{StopDaoImpl()}
}