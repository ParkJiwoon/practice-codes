package com.bcp0109.springwebfluxkotlin.mono.router

import com.bcp0109.springwebfluxkotlin.coroutine.corouter.CoRouterController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig {

    val log: Logger = LoggerFactory.getLogger(RouterConfig::class.java)

    @Bean
    fun route(controller: RouterController) = router {
        "/v4".nest {
            GET("/home", controller::home)
            GET("/google", controller::google)
            GET("/mine", controller::mine)
            GET("/signup", controller::signup)
            GET("/members/{memberId}", controller::findMember)
            GET("/members", controller::findAll)

            before { request ->
                log.info("Before Filter")
                request
            }

            after { _, response ->
                log.info("After Filter")
                response
            }
        }
    }
}
