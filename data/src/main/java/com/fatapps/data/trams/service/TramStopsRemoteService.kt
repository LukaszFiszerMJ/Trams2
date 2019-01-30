package com.fatapps.data.trams.service

import com.fatapps.data.trams.service.pojo.TramStopsResponse
import io.reactivex.Observable
import retrofit2.http.GET


interface TramStopsRemoteService {
    //https://pastebin.com/raw/S3QwgEbf
    @GET("/raw/S3QwgEbf/")
    fun getTramStops() : Observable<TramStopsResponse>
}