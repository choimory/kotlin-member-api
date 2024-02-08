package com.choimory.kotlinmemberapi.v1.member.controller

import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberFind
import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberJoin
import com.choimory.kotlinmemberapi.v1.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/{id}")
    fun find(@PathVariable id: Long): ResponseMemberFind {
        return memberService.find(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun join(): ResponseMemberJoin = ResponseMemberJoin()
}