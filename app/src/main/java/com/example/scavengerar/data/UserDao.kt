package com.example.scavengerar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the User class.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY created_at")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUser(userId: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)
}
