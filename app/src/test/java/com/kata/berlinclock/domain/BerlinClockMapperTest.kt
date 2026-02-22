package com.kata.berlinclock.domain

import com.kata.berlinclock.domain.model.LampColor
import com.kata.berlinclock.domain.model.LampStatus
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalTime

class BerlinClockMapperTest {

    @Test
    fun `00-00-00 should turn seconds lamp ON`() {
        val state = BerlinClockMapper.map(LocalTime.of(0, 0, 0))

        assertEquals(LampStatus.ON, state.secondsLamp.status)
    }

    @Test
    fun `00-00-01 should turn seconds lamp OFF`() {
        val state = BerlinClockMapper.map(LocalTime.of(0, 0, 1))

        assertEquals(LampStatus.OFF, state.secondsLamp.status)
    }

    @Test
    fun `13-00-00 should light 2 five-hour lamps and 3 one-hour lamps`() {
        val state = BerlinClockMapper.map(LocalTime.of(13, 0, 0))

        val fiveHoursOn = state.fiveHoursRow.count { it.status == LampStatus.ON }
        val oneHoursOn = state.oneHourRow.count { it.status == LampStatus.ON }

        assertEquals(2, fiveHoursOn)
        assertEquals(3, oneHoursOn)
    }

    @Test
    fun `00-32-00 should light 6 five-minute lamps and 2 one-minute lamps`() {
        val state = BerlinClockMapper.map(LocalTime.of(0, 32, 0))

        val fiveMinutesOn = state.fiveMinutesRow.count { it.status == LampStatus.ON }
        val oneMinutesOn = state.oneMinuteRow.count { it.status == LampStatus.ON }

        assertEquals(6, fiveMinutesOn)
        assertEquals(2, oneMinutesOn)
    }

    @Test
    fun `five-minute row quarter lamps should be red`() {
        val state = BerlinClockMapper.map(LocalTime.of(0, 32, 0))

        // positions 3 and 6 are quarter markers (1-indexed)
        assertEquals(LampColor.RED, state.fiveMinutesRow[2].color)
        assertEquals(LampColor.RED, state.fiveMinutesRow[5].color)
        // position 9 is also a quarter marker
        assertEquals(LampColor.RED, state.fiveMinutesRow[8].color)
    }

    @Test
    fun `23-59-59 edge case`() {
        val state = BerlinClockMapper.map(LocalTime.of(23, 59, 59))

        assertEquals(LampStatus.OFF, state.secondsLamp.status)
        assertEquals(4, state.fiveHoursRow.count { it.status == LampStatus.ON })
        assertEquals(3, state.oneHourRow.count { it.status == LampStatus.ON })
        assertEquals(11, state.fiveMinutesRow.count { it.status == LampStatus.ON })
        assertEquals(4, state.oneMinuteRow.count { it.status == LampStatus.ON })
    }
}