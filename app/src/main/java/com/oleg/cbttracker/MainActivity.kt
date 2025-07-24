package com.oleg.cbttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

/**
 * Simplest possible “Qualia-style” CBT tracker.
 *
 * * Jetpack Compose, single screen
 * * In-memory list (no database or ViewModel yet)
 * * FloatingActionButton opens a dialog to add a thought
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CbtTrackerApp() }
    }
}
