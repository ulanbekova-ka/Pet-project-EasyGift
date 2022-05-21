package com.kay.prog.easygift.data.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.models.WishEntity

@Dao
interface WishDao {
    @Query("SELECT * FROM WishEntity WHERE nickname = :nickname")
    fun getWishByNickname(nickname: String): LiveData<List<WishEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(wishes: List<WishEntity>)

    @Query("DELETE FROM WishEntity")
    fun clearTable()
}