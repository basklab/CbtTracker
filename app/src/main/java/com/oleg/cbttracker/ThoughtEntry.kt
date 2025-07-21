package com.oleg.cbttracker

import java.time.LocalDateTime
import java.util.UUID

/**
 * A single CBT “thought record”.
 *
 * For this very first tutorial step we only capture the free-text
 * thought plus a timestamp.  You’ll enrich this model later with
 * emotions, evidence-for/against, re-frames, etc.
 */
data class ThoughtEntry(
    val id: UUID = UUID.randomUUID(),
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val thought: String
)
