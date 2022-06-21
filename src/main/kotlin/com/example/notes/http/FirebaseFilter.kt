package com.example.notes.http

import com.example.notes.service.UserVerificationService
import com.google.firebase.auth.FirebaseAuthException
import com.nimbusds.oauth2.sdk.ErrorResponse
import org.apache.http.HttpException
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class FirebaseFilter : OncePerRequestFilter() {

    var userVerificationService: UserVerificationService? = null

    @ExceptionHandler(IllegalStateException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        //TODO refactor spring security conf
        if (request.requestURL.endsWith("/user/save")) {
            filterChain.doFilter(request, response)
            return
        }
        if (userVerificationService == null) {
            val servletContext = request.servletContext
            val webApplicationContext: WebApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(servletContext)!!
            userVerificationService = webApplicationContext.getBean(UserVerificationService::class.java)
        }
        val authenticationHeader = request.getHeader("Authorization")

        if (authenticationHeader == null) {
            forbiddenResponse(response, "no Authorization header in request")
        }
        try {
            SecurityContextHolder.getContext().authentication = PreAuthenticatedAuthenticationToken(
                userVerificationService!!.verifyUser(authenticationHeader), null
            )
        } catch (ex: FirebaseAuthException) {
            forbiddenResponse(response, ex.message!!)
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun forbiddenResponse(response: HttpServletResponse, message: String) {
        response.status = HttpStatus.FORBIDDEN.value();
        response.writer.write(message)
    }
}