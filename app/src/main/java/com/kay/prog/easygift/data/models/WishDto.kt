package com.kay.prog.easygift.data.models

data class WishDto (
    var nickname: String,
    var description: String,
    var webpage: String?,
    var price: Double?,
    var created: String,
    var updated: String?
)