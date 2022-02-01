package com.bcp0109.springwebfluxkotlin.coroutine

import com.bcp0109.springwebfluxkotlin.domain.Member
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class CoroutineMemberService(
    private val memberRepository: CoroutineMemberRepository
) {

    suspend fun signup(name: String, age: Int): Member {
        val member = Member(name, age)
        return memberRepository.save(member)
    }

    suspend fun findMemberById(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }

    suspend fun findAll(): Flow<Member> {
        return memberRepository.findAll()
    }
}
