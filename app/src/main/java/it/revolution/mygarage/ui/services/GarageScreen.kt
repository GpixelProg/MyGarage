package it.revolution.mygarage.ui.services

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.revolution.mygarage.domain.services.GetVehicleList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GarageScreen(idScreen: (Int) -> Unit) {
    val vehicleList = GetVehicleList()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { idScreen(1) }) {
                Icon(Icons.Default.Add, contentDescription = "Add vehicle")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Гараж",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(vehicleList.size) {
                        val vehicle = vehicleList[it]
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = vehicle.name, fontWeight = FontWeight.Bold)
                            Row {
                                Text(text = vehicle.brand + " " + vehicle.model)
                            }
                        }
                    }
                }
            }
        }
    )
}