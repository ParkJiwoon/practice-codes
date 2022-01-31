package com.bcp0109.springwebfluxkotlin.domain

import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Repository
class MemoryMemberRepository : MemberRepository {

    companion object {
        val store = ConcurrentHashMap<Long, Member>()
    }

    override fun save(member: Member): Mono<Member> {
        member.id = store.size + 1L
        store[member.id!!] = member
        return Mono.just(member)
    }

    override fun findById(id: Long): Mono<Member> {
        return Mono.justOrEmpty(store[id])
    }

}
