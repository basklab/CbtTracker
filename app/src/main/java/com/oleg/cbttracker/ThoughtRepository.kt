
package com.oleg.cbttracker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.UUID

class ThoughtRepository(private val dao: ThoughtEntryDao) {

    val entries: Flow<List<ThoughtEntry>> = dao.getAll()

    suspend fun add(thought: String) = withContext(Dispatchers.IO) {
        dao.insert(ThoughtEntry(thought = thought))
    }

    suspend fun update(id: UUID, thought: String) = withContext(Dispatchers.IO) {
        dao.updateThought(id.toString(), thought)
    }

    suspend fun get(id: UUID): ThoughtEntry? = withContext(Dispatchers.IO) {
        dao.getById(id.toString())
    }

    suspend fun delete(id: UUID) = withContext(Dispatchers.IO) {
        dao.deleteById(id.toString())
    }
}

