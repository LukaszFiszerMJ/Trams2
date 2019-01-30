package com.fatapps.trams.di

import com.fatapps.trams.MainViewModel
import com.fatapps.trams.TimeTableViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val ViewModelModule = module {


    viewModel {
        MainViewModel(
            get(),get(),get()
        )
    }


    viewModel { (stopId: String, stopName: String)
        -> TimeTableViewModel(stopId, stopName, get()) }
}