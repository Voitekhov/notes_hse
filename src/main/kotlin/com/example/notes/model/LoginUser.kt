package com.example.notes.model


class LoginUser private constructor(val id: Int, val email: String) {
    companion object {
        fun fromUser(user: User): LoginUser {
            return LoginUser(user.id!!, user.login)
        }
    }
}