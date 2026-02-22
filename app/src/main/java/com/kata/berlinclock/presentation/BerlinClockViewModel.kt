package com.kata.berlinclock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kata.berlinclock.domain.BerlinClockMapper
import com.kata.berlinclock.domain.model.BerlinClockState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Clock
import java.time.LocalTime

class BerlinClockViewModel (
    private val clock: Clock = Clock.systemDefaultZone()
) : ViewModel() {

    private val _state = MutableStateFlow(BerlinClockMapper.map(LocalTime.now(clock)))
    val state: StateFlow<BerlinClockState> = _state

    private val _currentTime = MutableStateFlow(LocalTime.now(clock))
    val currentTime: StateFlow<LocalTime> = _currentTime

    init {
        viewModelScope.launch {
            while (true) {
                val now = LocalTime.now(clock)
                _currentTime.value = now
                _state.value = BerlinClockMapper.map(now)
                delay(1000)
            }
        }
    }
}