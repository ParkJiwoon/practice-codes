package com.bcp0109.springwebfluxkotlin.web

import com.bcp0109.springwebfluxkotlin.domain.Member
import com.bcp0109.springwebfluxkotlin.domain.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import reactor.core.publisher.Mono

@RestController
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/")
    fun home(): Mono<String> {
        return Mono.just("Home")
    }

    @GetMapping("/google")
    fun google(): Mono<String> {
        val template = RestTemplate()
        val response = template.getForObject("https://www.google.com", String::class.java)
        return Mono.just(response!!)
    }

    @GetMapping("/members")
    fun signup(
        @RequestParam name: String,
        @RequestParam age: Int
    ): Mono<Member> {
        return memberService.signup(name, age)
    }

    @GetMapping("/members/{memberId}")
    fun findMember(
        @PathVariable("memberId") memberId: Long
    ): Mono<Member> {
        return memberService.findMemberById(memberId)
    }
}
