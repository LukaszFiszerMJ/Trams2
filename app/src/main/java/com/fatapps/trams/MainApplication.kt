package com.fatapps.trams

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.fatapps.trams.di.*
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger
import timber.log.Timber

open class MainApplication : Application() {

    override fun onCreate() {
        Log.d("MainApplication", "onCreate()")
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initRealm()
        initKoin()
    }

    protected open fun initRealm() {
        Timber.d("initRealm")
        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
            .schemaVersion(2)
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    protected fun initTimber() {
        Timber.plant(Timber.DebugTree())
        Timber.d("initTimber")
    }

    private fun initKoin() {
        Timber.d("initKoin")
        startKoin(
            this,
            listOf(
                AppModule,
                NetworkModule,
                UseCaseModule,
                DataModule,
                ViewModelModule,
                DeviceModule
            ),
            logger = EmptyLogger()
        )
    }
}