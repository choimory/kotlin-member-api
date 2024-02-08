package com.choimory.kotlinmemberapi.v1.member.repository

import com.choimory.kotlinmemberapi.v1.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long> {
}