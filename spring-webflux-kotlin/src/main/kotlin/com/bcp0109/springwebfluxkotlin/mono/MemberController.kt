package com.bcp0109.springwebfluxkotlin.mono

import com.bcp0109.springwebfluxkotlin.domain.Member
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/v1")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/home")
    fun home(): Mono<ResponseEntity<String>> {
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
