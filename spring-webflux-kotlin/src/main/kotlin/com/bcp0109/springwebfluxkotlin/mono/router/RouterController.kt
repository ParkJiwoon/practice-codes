package com.bcp0109.springwebfluxkotlin.mono.router
import com.bcp0109.springwebfluxkotlin.coroutine.corouter.CoRouterController
import com.bcp0109.springwebfluxkotlin.mono.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Controller
class RouterController(
    private val memberService: MemberService
) {
    val log: Logger = LoggerFactory.getLogger(CoRouterController::class.java)

    fun home(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().json().body(
            Mono.just("home")
        )
    }

    fun google(request: ServerRequest): Mono<ServerResponse> {
        val template = RestTemplate()
        val response = template.getForObject("http://www.google.com", String::class.java)

        return ServerResponse.ok().json().body(
            Mono.just(response!!)
        )
    }

    fun signup(request: ServerRequest): Mono<ServerResponse> {
        val name = request.queryParamOrNull("name")
        val age = request.queryParamOrNull("age")?.toInt()

        name ?: log.error("No Parameter Name")
        age ?: log.error("No Parameter Age")

        return ServerResponse.ok().json().body(
            memberService.signup(name!!, age!!)
        )
    }

    fun findMember(request: ServerRequest): Mono<ServerResponse> {
        val memberId = request.pathVariable("memberId").toLong()
        val member = memberService.findMemberById(memberId)

        return ServerResponse.ok().json().body(member)
    }

    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        val members = memberService.findAll()
        return ServerResponse.ok().json().body(members)
    }
}
