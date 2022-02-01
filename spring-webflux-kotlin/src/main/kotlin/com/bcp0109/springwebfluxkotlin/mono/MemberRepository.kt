package com.bcp0109.springwebfluxkotlin.mono

import com.bcp0109.springwebfluxkotlin.domain.Member
import reactor.core.publisher.Mono

interface MemberRepository {
    fun save(member: Member): Mono<Member>
    fun findById(id: Long): Mono<Member>
}
