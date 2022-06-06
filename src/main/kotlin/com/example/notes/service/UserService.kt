package com.example.notes.service

import com.example.notes.model.User
import com.example.notes.repository.UserJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService {
    @Autowired
    lateinit var userJPA: UserJPA

    @Autowired
    lateinit var userVerificationService: UserVerificationService

    fun get(): User {
        return userJPA.get(userVerificationService.getCurrentUser().id!!)
    }

    @Transactional
    fun save(user: User): User {
        return userJPA.save(user)
    }

}