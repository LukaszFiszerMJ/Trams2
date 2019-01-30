package com.fatapps.trams.di

import android.content.ContentResolver
import android.content.SharedPreferences
import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val AppModule = module {

    val ctx by lazy { androidApplication() }

    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(ctx) }
    single<ContentResolver> { ctx.contentResolver }
}