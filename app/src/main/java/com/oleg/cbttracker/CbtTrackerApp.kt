package com.oleg.cbttracker

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.oleg.cbttracker.components.AddThoughtDialog
import com.oleg.cbttracker.components.ThoughtListUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CbtTrackerApp() {
    val entries = remember { mutableStateListOf<ThoughtEntry>() }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add thought")
            }
        }
    ) { inner ->
        ThoughtListUI(
            entries = entries,
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        )

        if (showDialog) {
            AddThoughtDialog(
                onAdd = { text ->
                    entries.add(ThoughtEntry(thought = text))
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}
