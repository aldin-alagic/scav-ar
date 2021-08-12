package com.example.scavengerar.data

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling Level data operations.
 *
 * Collecting from the Flows in [LevelDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class LevelRepository @Inject constructor(private val levelDao: LevelDao) {

    fun getLevels() = levelDao.getLevels()

    fun getLevel(levelId: Int) = levelDao.getLevel(levelId)
}
