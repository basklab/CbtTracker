package com.oleg.cbttracker.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.cbttracker.Destinations
import com.oleg.cbttracker.ThoughtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val repo: ThoughtRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val existingId: UUID? = savedStateHandle.get<String>(Destinations.ARG_ID)
        ?.takeIf { it != "new" }
        ?.let(UUID::fromString)

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
