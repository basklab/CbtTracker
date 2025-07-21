
package com.oleg.cbttracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.oleg.cbttracker.components.ThoughtListUI
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var dao: ThoughtEntryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "thought_entries.db"
        ).build()
        dao = db.thoughtEntryDao()

        setContent {
            val entries = remember { mutableStateListOf<ThoughtEntry>() }
            var showDialog by remember { mutableStateOf(false) }

            // Observe Room DB and update UI
            LaunchedEffect(Unit) {
                dao.getAll().collectLatest {
                    entries.clear()
                    entries.addAll(it)
                }
            }

            ThoughtListUI(
                entries = entries,
                onAdd = { thought ->
                    lifecycleScope.launch {
                        dao.insert(ThoughtEntry(thought = thought))
                    }
                },
                showDialog = showDialog,
                setShowDialog = { showDialog = it }
            )
        }
    }
}
