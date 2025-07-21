package com.oleg.cbttracker.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oleg.cbttracker.ThoughtEntry
import java.time.format.DateTimeFormatter

@Composable
fun ThoughtListUI(entries: List<ThoughtEntry>, modifier: Modifier = Modifier) {
    if (entries.isEmpty()) {
        Box(modifier, contentAlignment = Alignment.Center) {
            Text("No entries yet. Tap ➕ to add one.")
        }
    } else {
        val fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        LazyColumn(modifier) {
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
}
