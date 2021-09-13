package com.example.scavengerar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the LevelItem class.
 */
@Dao
interface LevelItemDao {
    @Query("SELECT * FROM level_item ORDER BY user_level_id")
    fun getLevelItems(): Flow<List<LevelItem>>

    @Query("SELECT * FROM level_item WHERE item_id = :itemId")
    fun getLevelItem(itemId: Int): Flow<LevelItem>

    @Query("SELECT item.id, item.name, item.description, item.image, EXISTS(SELECT * FROM level_item WHERE item_id = item.id) as completed FROM item JOIN user_level ON item.level_id = user_level.level_id WHERE user_level.user_id = :userId AND user_level.completed = 0")
    fun getLevelItems(userId: Int): Flow<List<ActiveLevelItem>>

    @Query("SELECT item.id, item.name, item.description, item.image, EXISTS(SELECT * FROM level_item WHERE item_id = item.id) as completed FROM item JOIN user_level ON item.level_id = user_level.level_id WHERE user_level.user_id = :userId AND user_level.completed = 0 AND completed = 0")
    fun getActiveLevelItems(userId: Int): Flow<List<ActiveLevelItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<LevelItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LevelItem)
}
