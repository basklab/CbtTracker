
package com.oleg.cbttracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ThoughtEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: ThoughtEntry)

    @Query("UPDATE ThoughtEntry SET thought = :thought WHERE id = :id")
    suspend fun updateThought(id: String, thought: String)

    @Query("SELECT * FROM ThoughtEntry WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): ThoughtEntry?

    @Query("DELETE FROM ThoughtEntry WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM ThoughtEntry ORDER BY timestamp DESC")
    fun getAll(): Flow<List<ThoughtEntry>>

    @Query("DELETE FROM ThoughtEntry")
    suspend fun clearAll()
}

