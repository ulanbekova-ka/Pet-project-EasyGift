package com.kay.prog.easygift.data.models

data class UserDto(
    var id: Long,
    var name: String,
    var surname: String,
    var birthday: String,
    var avatarUrl: String
)