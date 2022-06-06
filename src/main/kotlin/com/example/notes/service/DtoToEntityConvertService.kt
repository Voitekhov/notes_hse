package com.example.notes.service

import com.example.notes.model.Group
import com.example.notes.model.Note
import com.example.notes.model.User
import com.example.notes.model.dto.GroupDto
import com.example.notes.model.dto.NoteDto
import com.example.notes.model.dto.UserDto
import com.example.notes.repository.GroupJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DtoToEntityConvertService {
    @Autowired
    private lateinit var groupJPA: GroupJPA

    @Autowired
    private lateinit var userVerificationService: UserVerificationService

    fun groupDtoToGroup(groupDto: GroupDto): Group {
        val user = userVerificationService.getCurrentUser()
        return Group(groupDto.id, groupDto.title, groupDto.description, groupDto.color, user)
    }

    fun noteDtoToNote(noteDto: NoteDto): Note {
        val user = userVerificationService.getCurrentUser()
        val group: Group? = if (noteDto.groupId != null) groupJPA.get(noteDto.groupId, user.id!!) else null
        return Note(noteDto.id, noteDto.title, noteDto.description, noteDto.timeLimit, noteDto.isDone, noteDto.color, user, group)
    }

    fun userDtoToUser(userDto: UserDto): User {
        return User(userDto.id, userDto.login, userDto.firstName, userDto.secondName)
    }
}