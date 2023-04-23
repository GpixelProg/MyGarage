package it.revolution.mygarage.domain.services

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import it.revolution.mygarage.data.api.BodyType
import it.revolution.mygarage.data.api.Brand
import it.revolution.mygarage.data.api.ModelVehicle
import it.revolution.mygarage.data.api.VehicleType
import it.revolution.mygarage.data.database.Vehicle
import kotlinx.serialization.json.Json

private val API_KEY = "KEsjDMyvcgi2dgxrxgGslr4PBbdl2UR6eqyUa36C"

class ApiRequests {
    private val client = HttpClient(Android)
    private val type = object : TypeToken<List<VehicleType>>() {}.type

    suspend fun vehicleType() : List<VehicleType> {
        val url = "https://developers.ria.com/auto/categories/?api_key=$API_KEY"
        val vehicleType: String = client.get(url).body()
        val list: List<VehicleType> = Gson().fromJson(vehicleType, type)
        return list
    }

    suspend fun bodyType(categoryId: Int) : List<BodyType> {
        val url = "https://developers.ria.com/auto/categories/$categoryId/bodystyles?api_key=$API_KEY"
        val bodyType: String = client.get(url).body()
        val list: List<BodyType> = Gson().fromJson(bodyType, type)
        return list
    }

    suspend fun brand(categoryId: Int) : List<Brand> {
        val url = "https://developers.ria.com/auto/categories/$categoryId/marks?api_key=$API_KEY"
        val brand: String = client.get(url).body()
        val list: List<Brand> = Gson().fromJson(brand, type)
        return list
    }

    suspend fun model(categoryId: Int) : List<ModelVehicle> {
        val url = "http://api.auto.ria.com/categories/$categoryId/marks/:markId/models?api_key=$API_KEY"
        val model: String = client.get(url).body()
        val list: List<ModelVehicle> = Gson().fromJson(model, type)
        return list
    }
}

fun GetVehicleList() : List<Vehicle> {
    return listOf<Vehicle>()
}

suspend fun getVehicleType() : List<String> {
    val types = mutableListOf<String>()
    ApiRequests().vehicleType().forEach {
        types.add(it.name)
    }

    return types
}