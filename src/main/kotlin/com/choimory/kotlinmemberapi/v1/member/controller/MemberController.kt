package com.choimory.kotlinmemberapi.v1.member.controller

import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberFind
import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberJoin
import com.choimory.kotlinmemberapi.v1.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/member")
@Validated
class MemberController(
    private val memberService: MemberService
) {

    //상세조회
    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseMemberFind {
        return memberService.find(id)
    }

    //목록조회
    @GetMapping
    fun findAll() {
        return memberService.findAll()
    }

    //회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun join(): ResponseMemberJoin {
        return memberService.join()
    }

    //로그인
    @PostMapping("/login")
    fun login() {
        return memberService.login()
    }
}