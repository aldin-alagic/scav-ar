package com.example.scavengerar.data

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling Item data operations.
 *
 * Collecting from the Flows in [ItemDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class ItemRepository @Inject constructor(private val itemDao: ItemDao) {

    fun getItems() = itemDao.getItems()

    fun getItem(itemId: Int) = itemDao.getItem(itemId)
}
