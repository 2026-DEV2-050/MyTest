package com.kata.berlinclock.domain

import com.kata.berlinclock.domain.model.BerlinClockState
import com.kata.berlinclock.domain.model.Lamp
import com.kata.berlinclock.domain.model.LampColor
import com.kata.berlinclock.domain.model.LampStatus
import java.time.LocalTime

object BerlinClockMapper {

    fun map(time: LocalTime): BerlinClockState {
        val secondsOn = time.second % 2 == 0

        val hours = time.hour

        val fiveHoursOn = hours / 5
        val oneHoursOn = hours % 5

        return BerlinClockState(
            secondsLamp = Lamp(
                color = LampColor.YELLOW,
                status = if (secondsOn) LampStatus.ON else LampStatus.OFF
            ),
            fiveHoursRow = solidRow(4, fiveHoursOn, LampColor.RED),
            oneHourRow = solidRow(4, oneHoursOn, LampColor.RED),
            fiveMinutesRow = emptyList(),
            oneMinuteRow = emptyList()
        )
    }

    private fun solidRow(total: Int, onCount: Int, color: LampColor): List<Lamp> =
        List(total) { index ->
            Lamp(
                color = color,
                status = if (index < onCount) LampStatus.ON else LampStatus.OFF
            )
        }
}