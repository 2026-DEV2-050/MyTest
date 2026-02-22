package com.kata.berlinclock.domain.model

data class BerlinClockState(
    val secondsLamp: Lamp,
    val fiveHoursRow: List<Lamp>,
    val oneHourRow: List<Lamp>,
    val fiveMinutesRow: List<Lamp>,
    val oneMinuteRow: List<Lamp>
)