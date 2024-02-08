package com.choimory.kotlinmemberapi.v1.member.domain.entity

import com.choimory.kotlinmemberapi.common.domain.entity.CommonDateTimeAt
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long, //고유번호
    val uuld: String, //UULD
    val email: String, //이메일
    password: String, //비밀번호
    nickname: String, //닉네임
    profile: String? = null, //회원소개
    @OneToOne(mappedBy = "member")
    val memberAuthority: MemberAuthority, //회원권한
    memberImages: MutableList<MemberImage>? = mutableListOf(),
) : CommonDateTimeAt(
    createdAt = LocalDateTime.now(), //생성일시
    modifiedAt = null, //수정일시
    deletedAt = null //삭제일시
) {
    //var 세터 캡슐화
    var password: String = password
        protected set
    var nickname: String = nickname
        protected set
    var profile: String? = profile
        protected set

    @OneToMany(mappedBy = "member")
    var memberImage: MutableList<MemberImage>? = mutableListOf()
        protected set

    //비밀번호 변경
    fun changePassword(newPassword: String) {
        this.password = newPassword;
    }

    //회원권한 변경
    fun changeAuth(changeAuth: MemberAuthority.Level) {
        this.memberAuthority.changeAuth(changeAuth)
    }
}