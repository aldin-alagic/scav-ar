package com.example.scavengerar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Item class.
 */
@Dao
interface ItemDao {
    @Query("SELECT * FROM item ORDER BY level_id")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * FROM item WHERE id = :itemId")
    fun getItem(itemId: Int): Flow<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Item>)
}
