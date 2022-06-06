package com.example.notes.model.dto

import com.example.notes.model.User

class UserDto private constructor(
    val id: Int?,
    val login: String,
    val firstName: String,
    val secondName: String
) {

    companion object {
        fun fromUser(user: User): UserDto {
            return UserDto(user.id, user.login, user.firstName, user.secondName)
        }
    }
}