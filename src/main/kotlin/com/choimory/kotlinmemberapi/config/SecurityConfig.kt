package com.choimory.kotlinmemberapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun config(http: HttpSecurity): SecurityFilterChain {
        http.formLogin { login -> login.disable() } //기본 로그인 비활성화
            .csrf { csrf -> csrf.disable() } //CSRF 비활성화

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}