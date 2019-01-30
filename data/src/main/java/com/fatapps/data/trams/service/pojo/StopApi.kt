package com.fatapps.data.trams.service.pojo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StopApi {

    @SerializedName("direction")
    @Expose
    var direction: String = ""

    @SerializedName("lat")
    @Expose
    var lat: Double = 0.0

    @SerializedName("line")
    @Expose
    var line: String = ""

    @SerializedName("lon")
    @Expose
    var lon: Double= 0.0

    @SerializedName("name")
    @Expose
    var name: String = ""

    @SerializedName("stopId")
    @Expose
    var stopId: String = ""
}