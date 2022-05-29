package com.kay.prog.easygift.domain.use_cases.db

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.User
import com.kay.prog.easygift.data.repo.UserRepo
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(id: Long): LiveData<User> {
        return userRepo.getUserInfo(id)
    }
}