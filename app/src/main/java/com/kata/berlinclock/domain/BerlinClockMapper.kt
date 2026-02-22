package com.kata.berlinclock.domain

import com.kata.berlinclock.domain.model.BerlinClockState
import com.kata.berlinclock.domain.model.Lamp
import com.kata.berlinclock.domain.model.LampColor
import com.kata.berlinclock.domain.model.LampStatus
import java.time.LocalTime

object BerlinClockMapper {

    fun map(time: LocalTime): BerlinClockState {
        val secondsOn = time.second % 2 == 0

        return BerlinClockState(
            secondsLamp = Lamp(
                color = LampColor.YELLOW,
                status = if (secondsOn) LampStatus.ON else LampStatus.OFF
            ),
            fiveHoursRow = emptyList(),
            oneHourRow = emptyList(),
            fiveMinutesRow = emptyList(),
            oneMinuteRow = emptyList()
        )
    }
}