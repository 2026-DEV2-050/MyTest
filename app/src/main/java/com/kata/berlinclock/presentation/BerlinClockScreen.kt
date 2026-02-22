package com.kata.berlinclock.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kata.berlinclock.domain.model.BerlinClockState
import com.kata.berlinclock.domain.model.Lamp
import com.kata.berlinclock.domain.model.LampColor
import com.kata.berlinclock.domain.model.LampStatus
import java.time.format.DateTimeFormatter

@Composable
fun BerlinClockScreen(vm: BerlinClockViewModel = viewModel()) {
    val state by vm.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(rememberScrollState())
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Seconds circle
            SecondsCircle(state.secondsLamp)

            // 5-hour row
            LargeLampRow(state.fiveHoursRow)

            // 1-hour row
            LargeLampRow(state.oneHourRow)

            // 5-minute row (11 lamps)
            SmallLampRow(state.fiveMinutesRow)

            // 1-minute row
            LargeLampRow(state.oneMinuteRow)

            Spacer(modifier = Modifier.height(32.dp))

            // Digital time
            DigitalTime(state)
        }
    }
}

@Composable
private fun SecondsCircle(lamp: Lamp) {
    val onColor = Color(0xFFFFEB3B)
    val offColor = Color.LightGray

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(if (lamp.status == LampStatus.ON) onColor else offColor)
            .border(4.dp, Color.DarkGray, CircleShape)
    )
}

@Composable
private fun LargeLampRow(lamps: List<Lamp>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        lamps.forEach { lamp ->
            LampBlock(
                lamp = lamp,
                height = 50.dp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SmallLampRow(lamps: List<Lamp>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        lamps.forEach { lamp ->
            LampBlock(
                lamp = lamp,
                height = 40.dp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun LampBlock(
    lamp: Lamp,
    height: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier
) {
    val onColor = when (lamp.color) {
        LampColor.RED -> Color.Red
        LampColor.YELLOW -> Color.Yellow
    }

    val offColor = Color.LightGray

    Box(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(12.dp))
            .background(if (lamp.status == LampStatus.ON) onColor else offColor)
            .border(4.dp, Color.DarkGray, RoundedCornerShape(12.dp))
    )
}

@Composable
private fun DigitalTime(state: BerlinClockState) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    Text(
        text = state.time.format(formatter),
        fontSize = 52.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground // 👈 HERE
    )
}