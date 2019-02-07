package com.fatapps.data.trams.service.pojo
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StopApi {

    @SerializedName("direction")
    var direction: String = ""

    @SerializedName("lat")
    var lat: Double = 0.0

    @SerializedName("line")
    var line: String = ""

    @SerializedName("lon")
    var lon: Double= 0.0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("stopId")
    var stopId: String = ""
}