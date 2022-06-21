package com.example.notes.service

import FIREBASE_APP
import com.example.notes.model.User
import com.example.notes.repository.UserJPA
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

import java.lang.RuntimeException

@Service
class UserVerificationService {

    @Autowired
    lateinit var userJPA: UserJPA

    fun verifyUser(token: String): User {
        val email = FirebaseAuth.getInstance(FIREBASE_APP).verifyIdToken(token).email ?: throw RuntimeException("Can't verify user")
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null && email == authentication.principal) {
            return (authentication.principal as User)
        }
        val user: User = userJPA.getByEmail(email)
        return user
    }

    fun getCurrentUser(): User {
        return SecurityContextHolder.getContext().getAuthentication().principal as User
    }

}