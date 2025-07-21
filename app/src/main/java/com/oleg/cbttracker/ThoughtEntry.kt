
package com.oleg.cbttracker

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class ThoughtEntry(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val thought: String
)

