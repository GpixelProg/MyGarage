package it.revolution.mygarage

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import it.revolution.mygarage.domain.services.ApiRequests
import it.revolution.mygarage.domain.services.nowDate
import it.revolution.mygarage.domain.services.startTimer
import it.revolution.mygarage.ui.services.GarageScreen
import it.revolution.mygarage.ui.services.VehicleTypeScreen
import it.revolution.mygarage.ui.theme.MyGarageTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.until
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGarageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var idScreen by remember { mutableStateOf(0) }
                    when(idScreen) {
                        0 -> {
                            GarageScreen(idScreen = { idScreen = it })
                        }

                        1 -> {
                            VehicleTypeScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Other() {
    LaunchedEffect(Unit) { startTimer() }
    val kotlinReleaseDateTime = LocalDateTime(2023, 4, 22, 16, 57, 0, 0).date.toEpochDays()

    val date = LocalDate.fromEpochDays(kotlinReleaseDateTime)

    val updDate = date.plus(1, DateTimeUnit.MONTH)

    Column {
        Text(
            text = "${
                Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.daysUntil(
                    date
                )
            }"
        )

        var dateNow by remember { mutableStateOf(nowDate()) }
        LaunchedEffect(Unit) {
            while (true) {
                dateNow = nowDate()
                delay(200)
            }
        }

        Text(text = dateNow.toString())

        var carList by remember { mutableStateOf("") }
        LaunchedEffect(Unit) {
            delay(1000)
            carList = ApiRequests().bodyType(1).toString()
        }

        Text(text = carList)
    }
}