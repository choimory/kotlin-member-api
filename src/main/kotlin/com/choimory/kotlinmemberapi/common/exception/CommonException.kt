package com.choimory.kotlinmemberapi.common.exception

import org.springframework.http.HttpStatus

class CommonException(
    val httpStatus: HttpStatus,
    val code: Int,
    override val message: String,
) : RuntimeException(
)