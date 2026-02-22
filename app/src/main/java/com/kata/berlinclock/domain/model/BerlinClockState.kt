package com.kata.berlinclock.domain.model

import java.time.LocalTime

data class BerlinClockState(
    val time: LocalTime,
    val secondsLamp: Lamp,
    val fiveHoursRow: List<Lamp>,
    val oneHourRow: List<Lamp>,
    val fiveMinutesRow: List<Lamp>,
    val oneMinuteRow: List<Lamp>
)