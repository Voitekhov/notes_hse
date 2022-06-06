package com.example.notes.http

import com.example.notes.service.UserVerificationService
import org.apache.http.HttpException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class FirebaseFilter : OncePerRequestFilter() {

    var userVerificationService: UserVerificationService? = null

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        println("HEAR!")
        if (userVerificationService == null) {
            val servletContext = request.servletContext
            val webApplicationContext: WebApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(servletContext)!!
            userVerificationService = webApplicationContext.getBean(UserVerificationService::class.java)
        }

        val authenticationHeader = request.getHeader("Authorization") ?: throw HttpException("no Authorization header in request")

        SecurityContextHolder.getContext().authentication = PreAuthenticatedAuthenticationToken(
            userVerificationService!!.verifyUser(authenticationHeader), null
        )
        filterChain.doFilter(request, response)
    }
}