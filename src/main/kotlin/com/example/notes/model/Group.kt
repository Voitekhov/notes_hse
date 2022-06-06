package com.example.notes.model

import BaseEntity
import com.fasterxml.jackson.annotation.JsonProperty
import net.minidev.json.annotate.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "groups")
data class Group(
    override val id: Int?,
    @Column(name = "title")
    val title: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "color")
    val color: String,
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User::class)
    @JoinColumn(name = "user_id")
    var user: User,
) : BaseEntity(id) {
}