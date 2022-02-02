package com.bcp0109.springwebfluxkotlin.mono.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig {

    @Bean
    fun route(controller: RouterController) = router {
        "/v4".nest {
            GET("/home", controller::home)
            GET("/google", controller::google)
            GET("/signup", controller::signup)
            GET("/members/{memberId}", controller::findMember)
            GET("/members", controller::findAll)
        }
    }
}
