package com.bcp0109.springwebfluxkotlin.coroutine.corouter

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBodyOrNull
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CoRouterConfig {

    /**
     * before 와 filter 는 먼저 선언한게 먼저 실행됨
     * after 와 filter 는 먼저 선언하게 나중에 실행됨
     * 헷갈리지 before&after 만 쓰거나 filter 만 쓰거나 하는게 나을거같음..
     *
     * This is MyFilter : WebFilter
     * This is Before
     * This is Filter: before call
     * This is Filter: before call 2
     * 2022-02-04 00:51:48.552  INFO 88860 --- [ctor-http-nio-2] c.b.s.c.corouter.CoRouterController      : HTTP GET /v3/signup
     * This is Filter: after call 2
     * This is Filter: after call
     * This is After
     */
    @Bean
    fun coRoute(controller: CoRouterController) = coRouter {
        "/v3".nest {
            GET("/home", controller::home)
            GET("/google", controller::google)
            GET("/google2", controller::google2)
            GET("/signup", controller::signup)
            POST("/signup2", controller::signup2)
            GET("/members/{memberId}", controller::findMember)
            GET("/members", controller::findAll)

            // before 와 filter 는 먼저 선언한 순서대로 실행됨
            before { request ->
                println("This is Before")
                request
            }

            // after 는 먼저 선언되어 있을 수록 나중에 발생
            after { _, response ->
                println("This is After")
                response
            }

            // 사실 around 와 비슷하게 보면 됨 실행 전/후를 다 넣을수잇음
            filter { request, filterFunction ->
                println("This is Filter: before call")
                val logRequest = logging(request)
                val response = filterFunction(logRequest)
                println("This is Filter: after call")
                response
            }

            // 나중에 선언된 filter 가 좀더 안쪽에서 실행됨
            filter { request, filterFunction ->
                println("This is Filter: before call 2")
                val response = filterFunction(request)
                println("This is Filter: after call 2")
                response
            }
        }
    }

    suspend fun logging(request: ServerRequest): ServerRequest {
        val requestBody = request.awaitBodyOrNull<String>()

        println("queryParam: ${request.queryParams()}")
        println("pathVariables: ${request.pathVariables()}")
        println("requestBody: $requestBody")

        return ServerRequest.from(request).body(requestBody ?: "").build()
    }
}
