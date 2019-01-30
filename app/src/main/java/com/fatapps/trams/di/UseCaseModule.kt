package com.fatapps.trams.di

import com.fatapps.domain.interactor.location.TrackLocationUseCase
import com.fatapps.domain.interactor.trams.FetchTramStopsUseCase
import com.fatapps.domain.interactor.trams.GetTramTimeTableUseCase
import org.koin.dsl.module.module

val UseCaseModule = module {
    single{ FetchTramStopsUseCase(get()) }
    single{ GetTramTimeTableUseCase(get()) }
    single{TrackLocationUseCase(get())}
}