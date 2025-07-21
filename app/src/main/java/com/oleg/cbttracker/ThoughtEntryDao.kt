
package com.oleg.cbttracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ThoughtEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: ThoughtEntry)

    @Query("SELECT * FROM ThoughtEntry ORDER BY timestamp DESC")
    fun getAll(): Flow<List<ThoughtEntry>>

    @Query("DELETE FROM ThoughtEntry")
    suspend fun clearAll()
}

