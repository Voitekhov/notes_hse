package com.example.notes.service

import com.example.notes.model.Group
import com.example.notes.repository.GroupJPA
import com.example.notes.repository.UserJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class GroupService {

    @Autowired
    lateinit var groupJPA: GroupJPA

    @Autowired
    lateinit var userVerificationService: UserVerificationService

    @Autowired
    lateinit var userJPA: UserJPA

    fun get(groupId: Int): Group {
        val userId = userVerificationService.getCurrentUser().id!!
        return groupJPA.get(groupId, userId)
    }

    fun getAll(): List<Group> {
        val userId = userVerificationService.getCurrentUser().id!!
        return groupJPA.getAll(userId)
    }

    @Transactional
    fun save(group: Group) : Group {
       return groupJPA.save(group)
    }

    @Transactional
    fun delete(groupId: Int): Boolean {
        val userId = userVerificationService.getCurrentUser().id!!
        return groupJPA.delete(groupId, userId) != 0
    }
}