package com.oleg.cbttracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThoughtEditScreen(
    text: String,
    onTextChange: (String) -> Unit,
    onSave: () -> Unit,
    onDelete: () -> Unit,
    isNew: Boolean,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onSave,
                icon = { Icon(Icons.Default.Delete, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0f)) },
                text = { Text("Save") }
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(if (isNew) "New Entry" else "Edit Entry") },
                actions = {
                    if (!isNew) {
                        IconButton(onClick = onDelete) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            )
        }
    ) { inner ->
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            label = { Text("Thought") },
            modifier = modifier
                .fillMaxWidth()
                .padding(inner)
                .padding(16.dp),
            maxLines = 6
        )
    }
}