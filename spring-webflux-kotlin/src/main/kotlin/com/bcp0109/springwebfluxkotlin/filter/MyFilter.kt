package com.bcp0109.springwebfluxkotlin.filter

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class MyFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        println("woody log start")
        println(exchange.request.queryParams)
        println(exchange.response.statusCode)
        println("woody log end")

        return chain.filter(exchange)
    }
}
