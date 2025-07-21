
package com.oleg.cbttracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oleg.cbttracker.ThoughtEntry
import com.oleg.cbttracker.ThoughtRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ListViewModel(repo: ThoughtRepository) : ViewModel() {

    val entries: StateFlow<List<ThoughtEntry>> = repo.entries.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
}

