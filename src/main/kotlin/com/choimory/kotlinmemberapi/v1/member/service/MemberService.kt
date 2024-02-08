package com.choimory.kotlinmemberapi.v1.member.service

import com.choimory.kotlinmemberapi.v1.member.domain.entity.Member
import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberFind
import com.choimory.kotlinmemberapi.v1.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.Throws

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Throws(NoSuchElementException::class)
    fun find(id:Long):ResponseMemberFind{
        val member: Member = memberRepository.findById(id)
            .orElseThrow();

        return ResponseMemberFind(member.id, member.nickname)
    }
}