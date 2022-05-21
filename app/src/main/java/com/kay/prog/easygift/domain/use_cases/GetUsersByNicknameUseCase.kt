package com.kay.prog.easygift.domain.use_cases

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.repo.UserRepo
import javax.inject.Inject

class GetUsersByNicknameUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(nickname: String): LiveData<List<UserEntity>> {
        return userRepo.getUsersByNickname(nickname)
    }
}