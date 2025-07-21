package com.oleg.cbttracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oleg.cbttracker.ThoughtEntry
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtListUI(
    entries: List<ThoughtEntry>,
    onAdd: (String) -> Unit,
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { setShowDialog(true) }) {
                Icon(Icons.Default.Add, contentDescription = "Add thought")
            }
        }
    ) { inner ->
        if (entries.isEmpty()) {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(inner),
                contentAlignment = Alignment.Center
            ) {
                Text("No entries yet. Tap ➕ to add one.")
            }
        } else {
            val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(inner)
            ) {
                items(entries) { entry ->
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(entry.timestamp.format(fmt), style = MaterialTheme.typography.labelSmall)
                            Spacer(Modifier.height(4.dp))
                            Text(entry.thought)
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AddThoughtDialog(
                onAdd = {
                    onAdd(it)
                    setShowDialog(false)
                },
                onDismiss = { setShowDialog(false) }
            )
        }
    }
}
