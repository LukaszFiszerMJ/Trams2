package com.fatapps.trams.di

import android.app.Application
import com.fatapps.data.trams.service.TimeTableRemoteService
import com.fatapps.data.trams.service.TramStopsRemoteService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import org.koin.dsl.module.module
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val CACHE_SIZE = 10L * 1024 * 1024

val NetworkModule = module {

    single { Cache(get<Application>().cacheDir, CACHE_SIZE) }

    single<OkHttpClient> {
        val cache = get<Cache>()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.NONE


        val client = OkHttpClient.Builder()

        client.cache(cache)
        client.addInterceptor{ chain ->
            val original = chain.request()
            val builder = original.newBuilder()

            builder.header("Accept", "application/json")
            val request = builder.method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }
        .addNetworkInterceptor(logging)

        client.build()
    }

    single<Retrofit> ("default"){
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://pastebin.com/")
            .client(get())
            .build()
    }

    single<Retrofit>("times") {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get<Gson>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("https://api.um.warszawa.pl/api/action/")
            .client(get())
            .build()
    }

    single<Gson> {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.create()
    }

    single<TramStopsRemoteService>{ get<Retrofit>("default")
        .create(TramStopsRemoteService::class.java) }

    single<TimeTableRemoteService>{ get<Retrofit>("times")
        .create(TimeTableRemoteService::class.java)
    }
}