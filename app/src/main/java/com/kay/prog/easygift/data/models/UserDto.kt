package com.kay.prog.easygift.data.models

data class UserDto(
    var id: Long,
    var birthday: String,
    var avatar: String?,
    var nickname: String,
    var name: String,
    var surname: String,
    var email: String,
    var password: String
)