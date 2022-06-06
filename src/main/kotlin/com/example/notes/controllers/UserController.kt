package com.example.notes.controllers

import com.example.notes.model.dto.UserDto
import com.example.notes.service.DtoToEntityConvertService
import com.example.notes.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var dtoToEntityConvertService: DtoToEntityConvertService

    @GetMapping
    fun getUser(): UserDto {
        return UserDto.fromUser(userService.get())
    }

    @PostMapping("/save")
    fun testPost(@RequestBody userDto: UserDto): UserDto {
        val user = dtoToEntityConvertService.userDtoToUser(userDto)
        return UserDto.fromUser(userService.save(user))
    }
}