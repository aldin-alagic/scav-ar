package com.example.scavengerar.di

import android.content.Context
import androidx.room.Room
import com.example.scavengerar.data.AppDatabase
import com.example.scavengerar.data.ItemDao
import com.example.scavengerar.data.LevelDao
import com.example.scavengerar.utilities.DATABASE_NAME
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
}
