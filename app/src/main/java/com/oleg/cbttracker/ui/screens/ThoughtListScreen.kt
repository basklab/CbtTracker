
package com.oleg.cbttracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oleg.cbttracker.ThoughtEntry
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtListScreen(
    entries: List<ThoughtEntry>,
    onAdd: () -> Unit,
    onSelect: (UUID) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { inner ->
        if (entries.isEmpty()) {
            Box(
                modifier
                    .fillMaxSize()
                    .padding(inner),
                contentAlignment = Alignment.Center
            ) {
                Text("No entries yet. Tap ➕ to add one.")
            }
        } else {
            val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            LazyColumn(
                modifier
                    .fillMaxSize()
                    .padding(inner)
            ) {
                items(entries, key = { it.id }) { entry ->
                    ListItem(
                        headlineContent = { Text(entry.thought) },
                        supportingContent = { Text(entry.timestamp.format(fmt)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(entry.id) }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                    Divider()
                }
            }
        }
    }
}

