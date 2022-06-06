package com.example.notes.model

import BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(
    override val id: Int?,
    @Column(name = "login")
    val login: String,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "second_name")
    val secondName: String
) : BaseEntity(id) {
}