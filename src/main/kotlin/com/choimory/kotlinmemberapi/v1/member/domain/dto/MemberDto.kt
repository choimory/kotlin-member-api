package com.choimory.kotlinmemberapi.v1.member.domain.dto

import com.choimory.kotlinmemberapi.v1.member.domain.entity.Member
import com.choimory.kotlinmemberapi.v1.member.domain.entity.MemberAuthority
import com.choimory.kotlinmemberapi.v1.member.domain.entity.MemberImage
import java.time.LocalDateTime
import java.util.stream.Collectors

class MemberDto(
    val id: Long,
    val uuld: String,
    val email: String,
    val password: String,
    val nickname: String,
    val profile: String?,
    val memberAuthority: MemberAuthorityDto,
    val memberImages: MutableList<MemberImageDto>? = mutableListOf(),
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null,
) {

    companion object {
        fun toDto(entity: Member): MemberDto {
            return MemberDto(
                id = entity.id,
                uuld = entity.uuld,
                email = entity.email,
                password = entity.password,
                nickname = entity.nickname,
                profile = entity.profile,
                memberAuthority = MemberAuthorityDto.toDto(entity.memberAuthority),
                memberImages = entity.memberImages?.stream()
                    ?.map { memberImage -> MemberImageDto.toDto(memberImage) }
                    ?.collect(Collectors.toUnmodifiableList()),
                createdAt = entity.createdAt,
                modifiedAt = entity.modifiedAt,
                deletedAt = entity.deletedAt
            )
        }
    }


    class MemberAuthorityDto(
        val id: Long,
        val auth: MemberAuthority.Auth,
        val createdAt: LocalDateTime,
        val modifiedAt: LocalDateTime? = null,
        val deletedAt: LocalDateTime? = null,
    ) {
        companion object {
            fun toDto(entity: MemberAuthority): MemberAuthorityDto {
                return MemberAuthorityDto(
                    id = entity.id,
                    auth = entity.auth,
                    createdAt = entity.createdAt,
                    modifiedAt = entity.modifiedAt,
                    deletedAt = entity.deletedAt
                )
            }
        }
    }

    class MemberImageDto(
        val id: Long,
        val type: MemberImage.Type,
        val originalFileName: String,
        val fileName: String,
        val filePath: String,
        val fileSize: Long,
        val thumbnailFileName: String,
        val thumbnailFilePath: String,
        val thumbnailFileSize: Long,
        val createdAt: LocalDateTime,
        val modifiedAt: LocalDateTime? = null,
        val deletedAt: LocalDateTime? = null,
    ) {
        companion object {
            fun toDto(entity: MemberImage): MemberImageDto {
                return MemberImageDto(
                    id = entity.id, type = entity.type,
                    originalFileName = entity.originalFileName,
                    fileName = entity.fileName,
                    filePath = entity.filePath,
                    fileSize = entity.fileSize,
                    thumbnailFileName = entity.thumbnailFileName,
                    thumbnailFilePath = entity.thumbnailFilePath,
                    thumbnailFileSize = entity.thumbnailFileSize,
                    createdAt = entity.createdAt,
                    modifiedAt = entity.modifiedAt,
                    deletedAt = entity.deletedAt,
                )
            }
        }
    }
}