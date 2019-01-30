package com.fatapps.data.trams.service

import com.fatapps.data.trams.service.pojo.TimeTableResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


//Przykładowe wywołanie - pobranie linii dostępnych na przystanku
//https://api.um.warszawa.pl/api/action/dbtimetable_get
// ?id=88cd555f-6f31-43ca-9de4-66c479ad5942&busstopId=wartość&busstopNr=wartość&apikey=wartość

//Przykładowe wywołanie - pobranie rozkładu jazdy dla linii
//https://api.um.warszawa.pl/api/action/dbtimetable_get?id=e923fa0e-d96c-43f9-ae6e-60518c9f3238&busstopId=wartość&busstopNr=wartość&line=wartość&apikey=wartość
//@GET("/friends")
//Call<ResponseBody> friends(@Query("page") int page);
//
//Calling with foo.friends(1) yields /friends?page=1.
interface TimeTableRemoteService {

    @GET("dbtimetable_get")
    fun getTimeTable(
                    @Query("id")id:String = "e923fa0e-d96c-43f9-ae6e-60518c9f3238",
                    @Query("apikey") apikey:String = "54102799-ea3d-4b42-9d7f-65ea8d54a6ac",
                    @Query("busstopId") busStopId:String,
                     @Query("busstopNr") busstopNr:String,
                     @Query("line") line:String ="35")
            : Observable<TimeTableResponse>


}