package com.example.notes.repository

import com.example.notes.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
interface UserJPA : JpaRepository<User, Int> {
    @Query("SELECT u FROM User u WHERE u.login = :email")
    fun getByEmail(@Param("email") email: String): User

    @Query("SELECT u FROM User u WHERE u.id = :id")
    fun get(@Param("id") id: Int): User
}