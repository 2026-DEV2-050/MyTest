package com.kata.berlinclock.domain.model

enum class LampColor { RED, YELLOW }
enum class LampStatus { ON, OFF }

data class Lamp(
    val color: LampColor,
    val status: LampStatus
)