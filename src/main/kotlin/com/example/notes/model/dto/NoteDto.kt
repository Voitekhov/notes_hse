package com.example.notes.model.dto

import com.example.notes.model.Note
import java.time.LocalDateTime

class NoteDto private constructor(
    val id: Int?,
    val title: String,
    val description: String,
    val timeLimit: LocalDateTime?,
    val isDone: Boolean,
    val color: String,
    val groupId: Int?
) {
    companion object {
        fun fromNote(note: Note): NoteDto {
            return NoteDto(note.id, note.title, note.description, note.timeLimit, note.isDone, note.color, note.group?.id)
        }
    }

}