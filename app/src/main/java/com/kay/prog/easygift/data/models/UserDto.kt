package com.kay.prog.easygift.data.models

data class UserDto(
    var id: Long? = null,
    var birthday: String,
    var avatar: String,
    var nickname: String
)