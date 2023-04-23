package it.revolution.mygarage.data.database

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Service : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var vehicleId: ObjectId = ObjectId.create()
    var serviceType: String = ""
    var isSingle: Boolean = false
    var lastDate: Int? = null
    var nextDate: Int? = null
    var period: Int? = null
}