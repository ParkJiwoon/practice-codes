package com.bcp0109.springwebfluxkotlin.coroutine

import com.bcp0109.springwebfluxkotlin.domain.Member
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import org.springframework.stereotype.Repository
import reactor.kotlin.adapter.rxjava.toFlowable
import reactor.kotlin.core.publisher.toFlux
import java.util.concurrent.ConcurrentHashMap

@Repository
class CoroutineMemberRepository {

    companion object {
        val store = ConcurrentHashMap<Long, Member>()
    }

    suspend fun save(member: Member): Member {
        member.id = store.size + 1L
        store[member.id!!] = member
        return member
    }

    suspend fun findById(id: Long): Member? {
        return store[id]
    }

    suspend fun findAll(): Flow<Member> {
        return store.values.asFlow()
    }
}
