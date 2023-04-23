package it.revolution.mygarage.domain.services

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import it.revolution.mygarage.data.database.Service
import it.revolution.mygarage.data.database.Vehicle

class Database {
    //connect to realmDB
    val configVehicle = RealmConfiguration.create(schema = setOf(Vehicle::class))
    val configService = RealmConfiguration.create(schema = setOf(Service::class))
    val realmVehicle: Realm = Realm.open(configVehicle)
    val realmService: Realm = Realm.open(configService)

    fun getVehicleList(): RealmResults<Vehicle> {
        return realmVehicle.query<Vehicle>().find()
    }

    fun setVehicle(vehicle: Vehicle) {
        realmVehicle.writeBlocking {
            copyToRealm(Vehicle().apply {
                name = vehicle.name
                vehicleType = vehicle.vehicleType
                bodyType = vehicle.bodyType
                brand = vehicle.brand
                model = vehicle.model
            })
        }
    }
}