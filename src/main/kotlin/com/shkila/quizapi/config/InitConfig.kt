package com.shkila.quizapi.config

import com.shkila.quizapi.service.InitService
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class InitConfig {
    @Bean
    open fun initDb(initService: InitService) = ApplicationRunner {
        initService.initCategories()
        initService.initQuestions()
    }
}