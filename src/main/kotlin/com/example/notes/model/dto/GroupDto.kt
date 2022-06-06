package com.example.notes.model.dto

import com.example.notes.model.Group

class GroupDto private constructor(
    val id: Int?,
    val title: String,
    val description: String,
    val color: String
) {

    companion object {
        fun fromGroup(group: Group): GroupDto {
            return GroupDto(group.id, group.title, group.description, group.color)
        }
    }
}