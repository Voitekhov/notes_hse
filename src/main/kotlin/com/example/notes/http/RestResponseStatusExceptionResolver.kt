package com.example.notes.http

import com.google.firebase.auth.FirebaseAuthException
import org.apache.http.HttpException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class RestResponseStatusExceptionResolver {

    @ExceptionHandler(
        value = [
            HttpException::class,
            IllegalArgumentException::class,
            EmptyResultDataAccessException::class,
            FirebaseAuthException::class]
    )
    fun handleException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        if (ex is IllegalArgumentException) {
            return ResponseEntity(ex.message, HttpStatus.FORBIDDEN)
        }
        if (ex is EmptyResultDataAccessException) {
            return ResponseEntity("Can't find entity", HttpStatus.NOT_FOUND)
        }
        return null
    }


}
