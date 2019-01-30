package com.fatapps.data.trams.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*


@RealmClass
open class StopModel : RealmObject(){
    //@PrimaryKey
    //var id = UUID.randomUUID().toString()
    var direction: String = ""
    var lat: Double = -1.00
    var line: String = ""
    var lon: Double = -1.00
    var name: String = ""
    @PrimaryKey
    var stopId: String = ""
}