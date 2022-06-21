package com.example.notes.conf

import com.example.notes.http.FirebaseFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.context.SecurityContextPersistenceFilter



@Configuration
@EnableWebSecurity
class SecuretyConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var firebaseFilter: FirebaseFilter

    // clear context FilterChainProxy
    //SecurityContextPersistenceFilter
    override fun configure(http: HttpSecurity) {
        // TODO ты вообще вменяемый??
        http.addFilterAt(firebaseFilter, SecurityContextPersistenceFilter::class.java)
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .anyRequest().permitAll()

    }

    /*  @PostConstruct
    fun enableAuthCtxOnSpawnedThreads() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL)
    }
*/
}