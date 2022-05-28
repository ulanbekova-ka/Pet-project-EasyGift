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
    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserById(id : Long): LiveData<UserEntity>

//    @Insert(onConflict = REPLACE)
//    fun insertList(users: List<UserEntity>)

    @Insert
    fun insert(user: UserEntity)
}