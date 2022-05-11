package com.kay.prog.easygift.extensions

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.UserEntity

fun UserDto.toUserEntity(): UserEntity{
    return UserEntity(
        id,
        name,
        surname,
        birthday,
        avatarUrl
    )
}

//fun UserDto.toUser(): User {
//    return User(
//        id,name,surname
//    )
//}