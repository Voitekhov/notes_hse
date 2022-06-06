package com.example.notes.service

import com.example.notes.model.Note
import com.example.notes.repository.NoteJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class NoteService {

    @Autowired
    lateinit var noteJPA: NoteJPA

    @Autowired
    lateinit var userVerificationService: UserVerificationService

    fun get(noteId: Int): Note {
        val userId = userVerificationService.getCurrentUser().id!!
        return noteJPA.get(noteId, userId)
    }

    fun getAll(): List<Note> {
        val userId = userVerificationService.getCurrentUser().id!!
        return noteJPA.getAll(userId)
    }

    fun getAllByGroup(groupId: Int): List<Note> {
        val userId = userVerificationService.getCurrentUser().id!!
        return noteJPA.getAllByGroup(userId, groupId)
    }

    fun getByIsDone(isDone: Boolean): List<Note>{
        val userId = userVerificationService.getCurrentUser().id!!
        return noteJPA.getByIsDone(userId, isDone)
    }

    fun getOverdue(deadline: LocalDateTime): List<Note>{
        val userId = userVerificationService.getCurrentUser().id!!
        return noteJPA.getOverdue(userId, deadline)
    }

    @Transactional
    fun delete(noteId: Int): Boolean {
        val userId = userVerificationService.getCurrentUser().id!!
        return noteJPA.delete(noteId, userId) != 0
    }

    @Transactional
    fun save(note: Note): Note {
        return noteJPA.save(note)
    }
}