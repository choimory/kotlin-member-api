package com.choimory.kotlinmemberapi.v1.member.domain.entity

import com.choimory.kotlinmemberapi.common.domain.entity.CommonDateTimeAt
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class MemberAuthority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, //고유번호
    @OneToOne
    @JoinColumn(name = "member_id")
    val member: Member? = null, //회원정보
    auth: Auth, //권한
) : CommonDateTimeAt(
    createdAt = LocalDateTime.now(),
    modifiedAt = null,
    deletedAt = null,
) {
    //회원권한 enum
    enum class Auth {
        ADMIN, MEMBER
    }

    //var 세터 캡슐화
    var auth: Auth = auth
        protected set

    //회원권한 변경
    fun changeAuth(changeAuth: Auth) {
        this.auth = changeAuth
    }
}