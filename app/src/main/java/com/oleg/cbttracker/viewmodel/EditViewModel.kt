
package com.oleg.cbttracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.cbttracker.ThoughtRepository
import kotlinx.coroutines.launch
import java.util.UUID

class EditViewModel(
    private val repo: ThoughtRepository,
    private val existingId: UUID?
) : ViewModel() {

    var text by mutableStateOf("")
        private set

    init {
        if (existingId != null) {
            viewModelScope.launch {
                repo.get(existingId)?.let { text = it.thought }
            }
        }
    }

    fun onTextChange(newText: String) {
        text = newText
    }

    fun save(onDone: () -> Unit) {
        viewModelScope.launch {
            if (existingId == null) {
                repo.add(text)
            } else {
                repo.update(existingId, text)
            }
            onDone()
        }
    }

    fun delete(onDone: () -> Unit) {
        val id = existingId ?: return
        viewModelScope.launch {
            repo.delete(id)
            onDone()
        }
    }
}

