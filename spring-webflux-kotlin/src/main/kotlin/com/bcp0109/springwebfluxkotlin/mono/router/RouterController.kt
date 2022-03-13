package com.bcp0109.springwebfluxkotlin.mono.router
import com.bcp0109.springwebfluxkotlin.mono.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class RouterController(
    private val memberService: MemberService
) {
    val log: Logger = LoggerFactory.getLogger(RouterController::class.java)

    fun home(request: ServerRequest): Mono<ServerResponse> {
        val time = System.currentTimeMillis()

        return ServerResponse.ok().json().body(
            Mono.fromCallable {
                (0..1_000_000_000).forEach {
                    if (it % 1000 == 0) {
                        log.info("Request [$time] for: $it")
                    }
                }
                "home"
            }
        )
    }

    fun google(request: ServerRequest): Mono<ServerResponse> {
        val template = RestTemplate()
        val response = template.getForObject("http://www.google.com", String::class.java)

        return ServerResponse.ok().json().body(
            Mono.just(response!!)
        )
    }

    fun mine(request: ServerRequest): Mono<ServerResponse> {
        log.info("mine request")
        val webClient = WebClient.builder()
            .filters { it.add(logRequest()) }
            .build()

        return webClient.get()
            .uri("http://localhost:8181/mine")
            .retrieve()
            .bodyToMono(String::class.java)
            .flatMap {
                ServerResponse.ok().json().body(
                    Mono.just(it)
                )
            }
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

    fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest ->
            log.info("webclient request")
            Mono.just(clientRequest)
        }
    }
}
