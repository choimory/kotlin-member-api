package com.choimory.kotlinmemberapi.v1.member.service

import com.choimory.kotlinmemberapi.v1.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {
}