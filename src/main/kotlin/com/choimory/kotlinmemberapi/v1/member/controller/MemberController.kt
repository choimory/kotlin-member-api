package com.choimory.kotlinmemberapi.v1.member.controller

import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberFind
import com.choimory.kotlinmemberapi.v1.member.domain.response.ResponseMemberJoin
import com.choimory.kotlinmemberapi.v1.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/member")
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/{id}")
    fun find(@PathVariable id:Long):ResponseMemberFind{
        return ResponseMemberFind(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun join():ResponseMemberJoin = ResponseMemberJoin()
}