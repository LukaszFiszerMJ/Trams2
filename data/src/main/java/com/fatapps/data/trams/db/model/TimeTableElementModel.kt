package com.fatapps.data.trams.db.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class TimeTableElementModel : RealmObject(){
    @PrimaryKey
    var id = ""
    var stopId: String = ""
    var stopNumber: String = ""
    var hour: String = ""
    var minute: String = ""
}