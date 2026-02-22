package com.kata.berlinclock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.kata.berlinclock.presentation.BerlinClockScreen
import com.kata.berlinclock.presentation.BerlinClockViewModel
import com.kata.berlinclock.ui.theme.BerlinClockTheme

class MainActivity : ComponentActivity() {
    private val vm by viewModels<BerlinClockViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BerlinClockTheme {
                BerlinClockScreen()
            }
        }
    }
}
