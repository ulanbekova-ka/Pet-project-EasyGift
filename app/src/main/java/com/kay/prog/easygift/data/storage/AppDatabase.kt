package com.kay.prog.easygift.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.data.models.WishEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "user.db"
    }
}

@Database(entities = [WishEntity::class], version = 2)
abstract class AppDatabaseWish: RoomDatabase()  {

    abstract fun wishDao(): WishDao

    companion object {
        const val DB_NAME = "wish.db"
    }
}