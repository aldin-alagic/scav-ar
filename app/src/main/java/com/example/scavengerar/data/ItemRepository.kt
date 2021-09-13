package com.example.scavengerar.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling Item data operations.
 *
 * Collecting from the Flows in [ItemDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class ItemRepository @Inject constructor(private val itemDao: ItemDao, private val levelItemDao: LevelItemDao) {

    fun getItems() = itemDao.getItems()

    fun getItem(itemId: Int) = itemDao.getItem(itemId)

    fun getLevelItems(userId: Int) = levelItemDao.getLevelItems(userId)

    fun getActiveLevelItems(userId: Int) = levelItemDao.getActiveLevelItems(userId)


    suspend fun markItemAsFound(itemId: Int, userLevelId: Int) {
        val item = LevelItem(itemId, userLevelId, System.currentTimeMillis())
        levelItemDao.insert(item)
    }
}
