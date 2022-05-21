package com.kay.prog.easygift.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kay.prog.easygift.data.storage.AppDatabase
import com.kay.prog.easygift.data.storage.AppDatabaseWish
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()

    @Provides
    fun provideWishDao(appDatabase: AppDatabaseWish) = appDatabase.wishDao()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Application) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideAppDatabaseWish(context: Application) =
        Room.databaseBuilder(
            context,
            AppDatabaseWish::class.java,
            AppDatabaseWish.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
}