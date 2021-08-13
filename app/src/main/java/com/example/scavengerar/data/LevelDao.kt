package com.example.scavengerar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Level class.
 */
@Dao
interface LevelDao {
    @Query("SELECT * FROM level ORDER BY difficulty")
    fun getLevels(): Flow<List<Level>>

    @Query("SELECT * FROM level WHERE id = :levelId")
    fun getLevel(levelId: Int): Flow<Level>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(levels: List<Level>)
}
