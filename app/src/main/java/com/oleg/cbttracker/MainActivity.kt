
package com.oleg.cbttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.room.Room
import com.oleg.cbttracker.ui.theme.CbtTrackerTheme

class MainActivity : ComponentActivity() {

    private val repo by lazy {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "thought_entries.db"
        )
            .fallbackToDestructiveMigration()
            .build()
        ThoughtRepository(db.thoughtEntryDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CbtTrackerTheme {
                CbtTrackerApp(repo)
            }
        }
    }
}

