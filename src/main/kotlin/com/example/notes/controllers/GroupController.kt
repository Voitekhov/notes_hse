package com.example.notes.controllers

import com.example.notes.model.dto.GroupDto
import com.example.notes.service.DtoToEntityConvertService
import com.example.notes.service.GroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/groups/")
class GroupController {

    @Autowired
    lateinit var groupService: GroupService

    @Autowired
    lateinit var dtoToEntityConvertService: DtoToEntityConvertService

    @GetMapping("{groupId}")
    fun get(@PathVariable groupId: Int): GroupDto {
        return GroupDto.fromGroup(groupService.get(groupId))
    }

    @GetMapping("all")
    fun getAll(): List<GroupDto> {
        val groups = groupService.getAll()
        return groups.map { g -> GroupDto.fromGroup(g) }
    }

    @PostMapping("save")
    fun save(@RequestBody groupDto: GroupDto): GroupDto {
        val group = dtoToEntityConvertService.groupDtoToGroup(groupDto)
        return GroupDto.fromGroup(groupService.save(group))
    }

    @DeleteMapping("{groupId}")
    fun delete(@PathVariable groupId: Int): Boolean {
        return groupService.delete(groupId)
    }
}