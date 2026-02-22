package com.kata.berlinclock.domain

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

}