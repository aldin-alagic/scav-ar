package com.example.scavengerar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the UserLevel class.
 */
@Dao
interface UserLevelDao {
    @Query("SELECT * FROM user_level WHERE user_id = :userId")
    fun getUserLevels(userId: Int): Flow<List<UserLevel>>

    @Query("SELECT * FROM user_level WHERE user_id = :userId AND level_id = :levelId")
    fun getUserLevel(userId: Int, levelId: Int): Flow<UserLevel>

    @Query("SELECT * FROM user_level WHERE user_id = :userId AND completed = 0")
    fun getActiveLevel(userId: Int): Flow<UserLevel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserLevel>)
}
