package com.kay.prog.easygift.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.kay.prog.easygift.data.models.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity")
    fun getAll(): LiveData<MutableList<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserById(id : Long): LiveData<UserEntity>

    @Query("DELETE FROM UserEntity")
    fun deleteAll()

    @Insert(onConflict = REPLACE)
    fun insertUser(user: UserEntity)
}