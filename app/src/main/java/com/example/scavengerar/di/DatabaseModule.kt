package com.example.scavengerar.di

import android.content.Context
import com.example.scavengerar.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideLevelDao(database: AppDatabase): LevelDao {
        return database.levelDao()
    }

    @Provides
    fun provideItemDao(database: AppDatabase): ItemDao {
        return database.itemDao()
    }

    @Provides
    fun provideLevelItemDao(database: AppDatabase): LevelItemDao {
        return database.levelItemDao()
    }

    @Provides
    fun provideRoleDao(database: AppDatabase): RoleDao {
        return database.roleDao()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideUserLevelDao(database: AppDatabase): UserLevelDao {
        return database.userLevelDao()
    }
}
