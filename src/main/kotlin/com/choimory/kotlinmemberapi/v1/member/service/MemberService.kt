package com.choimory.kotlinmemberapi.v1.member.service

import com.choimory.kotlinmemberapi.common.exception.CommonException
import com.choimory.kotlinmemberapi.v1.member.domain.dto.MemberDto
import com.choimory.kotlinmemberapi.v1.member.domain.entity.Member
import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberFind
import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberJoin
import com.choimory.kotlinmemberapi.v1.member.repository.MemberRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
    @Throws(CommonException::class)
    fun find(id: Long): ResponseMemberFind {
        val member: Member = memberRepository.findById(id)
            .orElseThrow {
                CommonException(
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.reasonPhrase
                )
            }

        return ResponseMemberFind(MemberDto.toDto(member))
    }

    fun findAll(){}

    fun join() : ResponseMemberJoin{
        return ResponseMemberJoin()
    }

    fun login(){}
}