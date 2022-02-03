package com.bcp0109.springwebfluxkotlin.coroutine.corouter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CoRouterConfig {

    @Bean
    fun coRoute(controller: CoRouterController) = coRouter {
        "/v3".nest {
            GET("/home", controller::home)
            GET("/google", controller::google)
            GET("/google2", controller::google2)
            GET("/signup", controller::signup)
            GET("/members/{memberId}", controller::findMember)
            GET("/members", controller::findAll)
        }
    }
}
