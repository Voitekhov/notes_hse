package com.example.notes.repository

import com.example.notes.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
interface GroupJPA : JpaRepository<Group, Int> {

    @Query("SELECT g FROM Group g WHERE g.user.id = :userId AND g.id = :groupId")
    fun get(@Param("groupId") groupId: Int, @Param("userId") userId: Int): Group

    @Query("DELETE FROM Group g WHERE g.user.id = :userId AND g.id = :groupId")
    @Modifying
    fun delete(@Param("groupId") groupId: Int, @Param("userId") userId: Int) : Int

    @Query("SELECT g FROM Group g WHERE g.user.id = :userId")
    fun getAll(@Param("userId") userId: Int): List<Group>

/*    @Query("UPDATE Group g  SET g.title = :title, g.description = :description, g.color = :color WHERE g.user.id = :userId AND g.id = :groupId")
    fun update(
        @Param("groupId") groupId: Int,
        @Param("userId") userId: Int,
        @Param("title") title: String,
        @Param("description") description: String,
        @Param("color") color: String
    ): Group*/


}