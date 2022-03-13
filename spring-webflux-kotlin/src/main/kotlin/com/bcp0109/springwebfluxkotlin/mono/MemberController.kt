package com.bcp0109.springwebfluxkotlin.mono

import com.bcp0109.springwebfluxkotlin.coroutine.corouter.CoRouterController
import com.bcp0109.springwebfluxkotlin.domain.Member
import org.omg.CORBA.ServerRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Controller
@RequestMapping("/v1")
class MemberController(
    private val memberService: MemberService
) {
    val log: Logger = LoggerFactory.getLogger(MemberController::class.java)

    @GetMapping("/home")
    fun home(): Mono<ResponseEntity<String>> {
        log.info("home request")
        return Mono.just(
            ResponseEntity.ok().body("Home")
        )
    }

    @GetMapping("/google")
    fun google(): Mono<String> {
        val template = RestTemplate()
        val response = template.getForObject("https://www.google.com", String::class.java)
        return Mono.just(response!!)
    }

    @GetMapping("/api/mine")
    fun google2(): Mono<ResponseEntity<String>> {
        log.info("mine request")
        val webClient = WebClient.builder().build()

        return webClient.get()
            .uri("http://localhost:8181/mine")
            .retrieve()
            .bodyToMono(String::class.java)
            .map {
                ResponseEntity.ok().body(it)
            }
    }

    @GetMapping("/signup")
    fun signup(
        @RequestParam name: String,
        @RequestParam age: Int
    ): Mono<ResponseEntity<Member>> {
        return memberService.signup(name, age)
            .flatMap {
                Mono.just(ResponseEntity.ok(it))
            }
    }

    @GetMapping("/members/{memberId}")
    fun findMember(
        @PathVariable("memberId") memberId: Long
    ): Mono<ResponseEntity<Member>> {
        return memberService.findMemberById(memberId)
                .flatMap {
                    Mono.just(ResponseEntity.ok(it))
                }
    }
}
