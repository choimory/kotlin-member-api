package com.choimory.kotlinmemberapi.common.domain.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class CommonDateTimeAt(
    @CreatedDate
    protected var createdAt:LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    protected var modifiedAt:LocalDateTime? = null,

    protected var deletedAt:LocalDateTime? = null,
) :Serializable