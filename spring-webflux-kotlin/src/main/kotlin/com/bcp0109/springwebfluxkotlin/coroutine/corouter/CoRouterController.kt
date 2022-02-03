package com.bcp0109.springwebfluxkotlin.coroutine.corouter

import com.bcp0109.springwebfluxkotlin.coroutine.CoroutineMemberService
import com.bcp0109.springwebfluxkotlin.domain.Member
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import org.springframework.web.reactive.function.server.*

@Controller
class CoRouterController(
    private val memberService: CoroutineMemberService,
) {
    val log: Logger = LoggerFactory.getLogger(CoRouterController::class.java)

    suspend fun home(request: ServerRequest): ServerResponse {
        log.info("$request")
        return ServerResponse.ok().json().bodyValueAndAwait("home")
    }

    suspend fun google(request: ServerRequest): ServerResponse {
        log.info("$request")
        val template = RestTemplate()
        val response = template.getForObject("http://www.google.com", String::class.java)
        return ServerResponse.ok().json().bodyValueAndAwait(response!!)
    }

    suspend fun google2(request: ServerRequest): ServerResponse {
        log.info("$request")
        val webClient = WebClient.builder().build()

        val response = webClient.get()
            .uri("http://www.google.com")
            .retrieve()
            .awaitBody<String>()

        return ServerResponse.ok().json().bodyValueAndAwait(response)
    }

    suspend fun signup(request: ServerRequest): ServerResponse {
        log.info("$request")
        val name = request.queryParamOrNull("name")
        val age = request.queryParamOrNull("age")?.toInt()

        name ?: log.error("No Parameter Name")
        age ?: log.error("No Parameter Age")

        return ServerResponse.ok().json().bodyValueAndAwait(
            memberService.signup(name!!, age!!)
        )
    }

    suspend fun signup2(request: ServerRequest): ServerResponse {
        log.info("$request")
        val requestBody = request.awaitBody<Member>()

        return ServerResponse.ok().json().bodyValueAndAwait(
            memberService.signup(requestBody.name, requestBody.age)
        )
    }

    suspend fun findMember(request: ServerRequest): ServerResponse {
        log.info("$request")
        val memberId = request.pathVariable("memberId").toLong()
        val member = memberService.findMemberById(memberId)

        return ServerResponse.ok().json().bodyValueAndAwait(member ?: "Not Found Member $memberId")
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        log.info("$request")
        val members = memberService.findAll()
        return ServerResponse.ok().json().bodyAndAwait(members)
    }
}
