package com.kay.prog.easygift.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kay.prog.easygift.data.models.UserEntity
import com.kay.prog.easygift.extensions.TypeListConverter

@TypeConverters(TypeListConverter::class)
@Database(entities = [UserEntity::class], version = 8)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_NAME = "user.db"
    }
}