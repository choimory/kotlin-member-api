package com.choimory.kotlinmemberapi.v1.member.domain.entity

import com.choimory.kotlinmemberapi.common.domain.entity.CommonDateTimeAt
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class MemberImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    member: Member,
    @Enumerated(EnumType.STRING)
    val type: Type,
    val originalFileName: String,
    val fileName: String,
    val filePath: String,
    val fileSize: Long,
    val thumbnailFileName: String,
    val thumbnailFilePath: String,
    val thumbnailFileSize: Long,
) : CommonDateTimeAt(
    createdAt = LocalDateTime.now(),
    modifiedAt = null,
    deletedAt = null
) {
    enum class Type {
        BACK_GROUND, PROFILE
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member: Member = member
        protected set
}