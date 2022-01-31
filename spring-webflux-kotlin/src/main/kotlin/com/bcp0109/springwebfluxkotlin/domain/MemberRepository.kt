package com.bcp0109.springwebfluxkotlin.domain

import reactor.core.publisher.Mono

interface MemberRepository {
    fun save(member: Member): Mono<Member>
    fun findById(id: Long): Mono<Member>
}
