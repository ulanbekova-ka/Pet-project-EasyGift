package com.kay.prog.easygift.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kay.prog.easygift.data.models.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE nickname = :nickname")
    fun getUserByNickname(nickname: String): LiveData<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(users: List<UserEntity>)

    @Query("DELETE FROM UserEntity")
    fun deleteAll()

}