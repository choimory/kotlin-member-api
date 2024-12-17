package com.choimory.kotlinmemberapi.common.exception

import com.choimory.kotlinmemberapi.common.domain.response.ResponseCommon
import com.choimory.kotlinmemberapi.common.domain.response.ResponseCommonNotValid
import jakarta.validation.ConstraintViolationException
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

@RestControllerAdvice("com.choimory")
@Slf4j
class CommonExceptionAdvice {
    //전체 예외
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(e: Exception): ResponseCommon<String> {
        e.printStackTrace()
        return ResponseCommon(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            e.message
        )
    }

    //런타임 예외
    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun runtimeException(e: RuntimeException): ResponseCommon<String> {
        e.printStackTrace()
        return ResponseCommon(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
            e.message
        )
    }

    //공용 예외
    @ExceptionHandler(CommonException::class)
    fun commonException(e: CommonException): ResponseEntity<ResponseCommon<String>> {
        e.printStackTrace()
        return ResponseEntity(ResponseCommon(code = e.code, message = e.message), e.httpStatus)
    }


    //잘못된 요청 파라미터 오류
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseCommon<MutableList<ResponseCommonNotValid>> {
        e.printStackTrace()
        return ResponseCommon(
            code = HttpStatus.BAD_REQUEST.value(),
            message = HttpStatus.BAD_REQUEST.reasonPhrase,
            data = e.fieldErrors.stream()
                .map { fieldError ->
                    ResponseCommonNotValid(
                        field = fieldError.field,
                        rejectValue = fieldError.rejectedValue,
                        message = fieldError.defaultMessage
                    )
                }
                .collect(Collectors.toUnmodifiableList())
        )
    }

    //잘못된 요청 파라미터 오류
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun constraintViolationException(e: ConstraintViolationException): ResponseCommon<MutableList<ResponseCommonNotValid>> {
        e.printStackTrace()
        return ResponseCommon(
            code = HttpStatus.BAD_REQUEST.value(),
            message = HttpStatus.BAD_REQUEST.reasonPhrase,
            data = e.constraintViolations.stream()
                .map { constraintViolation ->
                    ResponseCommonNotValid(
                        field = constraintViolation.propertyPath.toString(),
                        rejectValue = constraintViolation.invalidValue,
                        message = constraintViolation.message
                    )
                }
                .collect(Collectors.toUnmodifiableList())
        )
    }
}