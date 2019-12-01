package com.shkila.quizapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
open class WebMvcConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS)
    }

    companion object {
        private const val MAX_AGE_SECS: Long = 3600L
    }
}
