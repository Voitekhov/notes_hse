/*
package com.example.notes.http

import org.apache.http.HttpException
import org.apache.http.HttpStatus
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseStatusExceptionResolver {

    @ExceptionHandler(value = [HttpException::class,IllegalArgumentException::class])
    fun handleException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any>? {
        if (ex is HttpException || ex is IllegalArgumentException) {
            return ResponseEntity(ex.message, org.springframework.http.HttpStatus.FORBIDDEN)
        }
        return null;
    }


}*/
