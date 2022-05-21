package com.kay.prog.easygift.domain.use_cases

import androidx.lifecycle.LiveData
import com.kay.prog.easygift.data.models.WishEntity
import com.kay.prog.easygift.data.repo.WishRepo
import javax.inject.Inject

class GetWishesByNicknameUseCase @Inject constructor(
    private val wishRepo: WishRepo
) {

    operator fun invoke(nickname: String): LiveData<List<WishEntity>> {
        return wishRepo.getWishByNickname(nickname)
    }
}