package com.kay.prog.easygift.domain.use_cases.db

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.repo.UserRepo
import javax.inject.Inject

class GetUsersFromDbUseCase @Inject constructor(
    private val userRepo: UserRepo
) {

    operator fun invoke(): LiveData<List<UserEntity>> {
        return userRepo.getUsersFromDB()
    }
}