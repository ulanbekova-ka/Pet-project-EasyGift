package com.kay.prog.easygift.extensions

import com.kay.prog.easygift.data.models.UserDto
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.models.WishDto
import com.kay.prog.easygift.data.models.WishEntity

fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id,
        birthday,
        avatar,
        nickname,
        name,
        surname,
        email,
        password
    )
}

fun WishDto.toWishEntity(): WishEntity {
    return WishEntity(
        null,
        nickname,
        description,
        webpage,
        price,
        created,
        updated
    )
}