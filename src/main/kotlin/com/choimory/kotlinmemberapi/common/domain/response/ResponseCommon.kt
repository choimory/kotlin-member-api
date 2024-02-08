package com.choimory.kotlinmemberapi.common.domain.response

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ResponseCommon<T>(
    val status: Int = HttpStatus.OK.value(),
    val message: String = HttpStatus.OK.reasonPhrase,
    val data: T? = null
)