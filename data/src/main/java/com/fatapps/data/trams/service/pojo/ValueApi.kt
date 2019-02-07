package com.fatapps.data.trams.service.pojo

import com.google.gson.annotations.SerializedName

class ValueApi(

    @SerializedName("key")
    var key: String? = "",
    @SerializedName("value")
    var value: String? = ""
)