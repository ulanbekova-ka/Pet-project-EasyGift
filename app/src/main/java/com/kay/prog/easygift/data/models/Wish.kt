package com.kay.prog.easygift.data.models

data class Wish (
    var id: Long,
    var nickname: String,
    var description: String,
    var webpage: String?,
    var price: Double?
)