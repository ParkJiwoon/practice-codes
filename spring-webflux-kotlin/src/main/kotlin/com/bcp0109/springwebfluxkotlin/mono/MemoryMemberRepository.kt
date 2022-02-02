package com.bcp0109.springwebfluxkotlin.mono

import com.bcp0109.springwebfluxkotlin.domain.Member
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Repository
class MemoryMemberRepository {

    companion object {
        val store = ConcurrentHashMap<Long, Member>()
    }

    fun save(member: Member): Mono<Member> {
        member.id = store.size + 1L
        store[member.id!!] = member
        return Mono.just(member)
    }

    fun findById(id: Long): Mono<Member> {
        return Mono.justOrEmpty(store[id])
    }

    fun findAll(): Flux<Member> {
        return Flux.fromIterable(store.values)
    }
}
