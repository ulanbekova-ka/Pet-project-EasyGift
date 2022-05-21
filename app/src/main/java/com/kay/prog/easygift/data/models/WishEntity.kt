package com.kay.prog.easygift.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WishEntity (
    @PrimaryKey
    var id: Long?,
    var nickname: String,
    var description: String,
    var webpage: String?,
    var price: Double?
)