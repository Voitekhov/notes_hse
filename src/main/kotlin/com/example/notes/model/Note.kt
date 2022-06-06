package com.example.notes.model

import BaseEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "notes")
data class Note(
    override val id: Int?,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "time_limit")
    val timeLimit: LocalDateTime?,
    @Column(name = "is_done")
    val isDone: Boolean,
    @Column(name = "color")
    val color: String,
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User::class)
    @JoinColumn(name = "user_id")
    var user: User,
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Group::class)
    @JoinColumn(name = "group_id")
    var group: Group?,
) : BaseEntity(id) {
}