package com.kay.prog.easygift.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    var birthday: String,
    var avatar: String?,
    var nickname: String,
    var name: String,
    var surname: String,
    var email: String,
    var password: String
)