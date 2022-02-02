package com.bcp0109.springwebfluxkotlin.coroutine

import com.bcp0109.springwebfluxkotlin.domain.Member
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@Controller
@RequestMapping("/v2")
class CoroutineMemberController(
    private val memberService: CoroutineMemberService
) {

    @GetMapping("/home")
    suspend fun home(): ResponseEntity<String> {
        return ResponseEntity.ok("Home")
    }

    @GetMapping("/google")
    suspend fun google(): String {
        val template = RestTemplate()
        val response = template.getForObject("http://www.google.com", String::class.java)
        return response!!
    }

    @GetMapping("/signup")
    suspend fun signup(
        @RequestParam name: String,
        @RequestParam age: Int
    ): ResponseEntity<Member> {
        return ResponseEntity.ok(
            memberService.signup(name, age)
        )
    }

    @GetMapping("/members/{memberId}")
    suspend fun findMember(
        @PathVariable("memberId") memberId: Long
    ): ResponseEntity<Member> {
        return ResponseEntity.ok(
            memberService.findMemberById(memberId)
        )
    }

    @FlowPreview
    @GetMapping("/members")
    suspend fun findAll(): ResponseEntity<Flow<Member>> {
        return ResponseEntity.ok(
            memberService.findAll()
        )
    }
}
