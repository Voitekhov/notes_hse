package com.example.notes.controllers

import com.example.notes.model.dto.NoteDto
import com.example.notes.service.DtoToEntityConvertService
import com.example.notes.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/notes/")
class NoteController {

    @Autowired
    private lateinit var noteService: NoteService

    @Autowired
    lateinit var dtoToEntityConvertService: DtoToEntityConvertService

    @GetMapping("{noteId}")
    fun get(@PathVariable noteId: Int): NoteDto {
        return NoteDto.fromNote(noteService.get(noteId))
    }

    @GetMapping("done")
    fun getByIsDone(@RequestParam("is_done") isDone: Boolean): List<NoteDto> {
        val notes = noteService.getByIsDone(isDone)
        return notes.map { n -> NoteDto.fromNote(n) }
    }

    @GetMapping("overdue")
    fun getOverdue(@RequestParam("deadline") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) deadline: LocalDateTime): List<NoteDto> {
        val notes = noteService.getOverdue(deadline)
        return notes.map { n -> NoteDto.fromNote(n) }
    }

    @GetMapping("all")
    fun getAll(): List<NoteDto> {
        return noteService.getAll().map { n -> NoteDto.fromNote(n) }
    }

    @GetMapping("all/{groupId}")
    fun getAllByGroup(@PathVariable groupId: Int): List<NoteDto> {
        return noteService.getAllByGroup(groupId).map { n -> NoteDto.fromNote(n) }
    }

    @PostMapping("/save")
    fun save(@RequestBody noteDto: NoteDto): NoteDto {
        val note = dtoToEntityConvertService.noteDtoToNote(noteDto)
        return NoteDto.fromNote(noteService.save(note))
    }

    @DeleteMapping("{noteId}")
    fun delete(@PathVariable noteId: Int): Boolean {
        return noteService.delete(noteId)
    }
}