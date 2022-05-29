package com.kay.prog.easygift.extensions

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.User

fun UserDto.toUserEntity(): User {
    return User(
        null,
        birthday,
        avatar,
        nickname,
        name,
        surname,
        email,
        password
    )
}