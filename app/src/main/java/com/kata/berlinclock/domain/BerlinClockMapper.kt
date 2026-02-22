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
        val minutes = time.minute

        val fiveHoursOn = hours / 5
        val oneHoursOn = hours % 5

        val fiveMinutesOn = minutes / 5
        val oneMinutesOn = minutes % 5

        return BerlinClockState(
            secondsLamp = Lamp(
                color = LampColor.YELLOW,
                status = if (secondsOn) LampStatus.ON else LampStatus.OFF
            ),
            fiveHoursRow = solidRow(4, fiveHoursOn, LampColor.RED),
            oneHourRow = solidRow(4, oneHoursOn, LampColor.RED),
            fiveMinutesRow = fiveMinutesRow(fiveMinutesOn),
            oneMinuteRow = solidRow(4, oneMinutesOn, LampColor.YELLOW)
        )
    }

    private fun solidRow(total: Int, onCount: Int, color: LampColor): List<Lamp> =
        List(total) { index ->
            Lamp(
                color = color,
                status = if (index < onCount) LampStatus.ON else LampStatus.OFF
            )
        }

    private fun fiveMinutesRow(onCount: Int): List<Lamp> =
        List(11) { index ->
            val isOn = index < onCount
            val isQuarter = (index + 1) % 3 == 0
            val color = if (isQuarter) LampColor.RED else LampColor.YELLOW

            Lamp(
                color = color,
                status = if (isOn) LampStatus.ON else LampStatus.OFF
            )
        }
}