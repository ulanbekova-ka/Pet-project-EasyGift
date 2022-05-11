package com.kay.prog.easygift.data.storage

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.Completable
import com.kay.prog.easygift.data.models.UserEntity

@Dao
interface UserDao {
    @Insert
    fun insertUsers(user: List<UserEntity>): Completable
}