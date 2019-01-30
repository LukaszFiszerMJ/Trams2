package com.fatapps.data.trams.service.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ValueApi(

    @SerializedName("key")
    @Expose
    var key: String? = "",
    @SerializedName("value")
    @Expose
    var value: String? = ""
)