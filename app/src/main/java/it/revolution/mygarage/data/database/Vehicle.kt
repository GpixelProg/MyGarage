package it.revolution.mygarage.data.database

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


class Vehicle : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var name: String = ""
    var vehicleType: String = ""
    var bodyType: String = ""
    var brand: String = ""
    var model: String = ""
}