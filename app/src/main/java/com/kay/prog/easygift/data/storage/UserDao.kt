package com.kay.prog.easygift.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kay.prog.easygift.data.models.User
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getAll(): LiveData<MutableList<User>>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserInfo(id : Long): LiveData<User>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id : Long): Single<User>

    @Query("DELETE FROM User")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)
}