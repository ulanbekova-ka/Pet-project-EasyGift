package com.kay.prog.easygift.extensions

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.domain.models.User


fun UserDto.toUserEntity(): UserEntity{
    return UserEntity(
        id,
        name,
        surname
    )
}


fun UserDto.toUser(): User {
    return User(
        id,name,surname
    )
}