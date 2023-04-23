package it.revolution.mygarage.ui.services

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.revolution.mygarage.domain.services.getVehicleType

@Composable
fun VehicleTypeScreen() {
    var selectedType by remember { mutableStateOf("") }
    var vehicleTypes = remember { mutableStateListOf("SERVER ERROR") }
    LaunchedEffect(Unit) {
        vehicleTypes.clear()
        vehicleTypes.addAll(getVehicleType())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Виберіть тип транспорту",
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        vehicleTypes.forEach { type ->
            val isSelected = type == selectedType

            Text(
                text = type,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .background(if (isSelected) Color(0xFFFFA500) else Color.Transparent)
                    .clickable { selectedType = type },
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /* перехід на наступний екран */ },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
        ) {
            Text(text = "Далі")
        }
    }
}
