package com.bcp0109.springwebfluxkotlin.coroutine.corouter

import com.bcp0109.springwebfluxkotlin.coroutine.CoroutineMemberService
import com.bcp0109.springwebfluxkotlin.domain.Member
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@ExtendWith(SpringExtension::class)
@WebFluxTest(CoRouterConfig::class, CoRouterController::class)
class CoRouterControllerTest {

    @Autowired
    private lateinit var client: WebTestClient

    @MockBean
    private lateinit var memberService: CoroutineMemberService

    @Test
    @DisplayName("전체 찾기 테스트")
    fun testFindAll(): Unit = runBlocking {
        given(
            memberService.findAll()
        ).willReturn(listOf(Member(name="woody", age=30)).asFlow())

        client.get()
            .uri("/v3/members")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").value(Matchers.`is`(1))

        verify(memberService).findAll()
    }

    @Test
    @DisplayName("가입하기 테스트")
    fun testSignup(): Unit = runBlocking {
        val name = "woody"
        val age = 30
        val member = Member(name, age)

        given(
            memberService.signup(anyString(), anyInt())
        ).willReturn(member)

        client.get()
            .uri("/v3/signup?name=$name&age=$age")
            .exchange()
            .expectStatus().isOk
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody<Member>().isEqualTo(member)

        verify(memberService).signup(anyString(), anyInt())
    }
}
