package com.kay.prog.easygift.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    var id: Long,
    var name: String,
    var surname: String,
    var birthday: String,
    var avatarUrl: String
)