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
    val createdAt: LocalDateTime = LocalDateTime.now(),
    modifiedAt: LocalDateTime?,
    deletedAt: LocalDateTime?,
) : Serializable {
    @LastModifiedDate
    var modifiedAt: LocalDateTime? = modifiedAt
        protected set
    var deletedAt: LocalDateTime? = modifiedAt
        protected set

}