package com.example.notes.repository

import com.example.notes.model.Note
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
interface NoteJPA : JpaRepository<Note, Int> {
    @Query("SELECT n FROM Note n WHERE n.id = :noteId AND n.user.id = :userId")
    fun get(
        @Param("noteId") noteId: Int,
        @Param("userId") userId: Int
    ): Note

    @Query("DELETE FROM Note n WHERE n.id = :noteId AND n.user.id = :userId")
    @Modifying
    fun delete(
        @Param("noteId") noteId: Int,
        @Param("userId") userId: Int
    ): Int

    @Query("SELECT n FROM Note n WHERE n.user.id = :userId")
    fun getAll(@Param("userId") userId: Int): List<Note>

    @Query("SELECT n FROM Note n WHERE n.user.id = :userId AND n.group.id = :groupId")
    fun getAllByGroup(@Param("userId") userId: Int, @Param("groupId") groupId: Int): List<Note>

    @Query("SELECT n FROM Note n WHERE n.isDone = :isDone AND n.user.id = :userId")
    fun getByIsDone(@Param("userId") userId: Int, @Param("isDone") isDone: Boolean): List<Note>

    @Query("SELECT n FROM Note n WHERE n.isDone = false AND n.timeLimit < :deadline AND n.user.id = :userId")
    fun getOverdue(@Param("userId") userId: Int, @Param("deadline") deadline: LocalDateTime): List<Note>
}