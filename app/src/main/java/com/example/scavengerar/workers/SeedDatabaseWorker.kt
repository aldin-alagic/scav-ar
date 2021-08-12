package com.example.scavengerar.workers

import android.content.Context
import android.util.Log
import android.util.Log.INFO
import androidx.hilt.work.HiltWorker
import dagger.assisted.AssistedInject
import dagger.assisted.Assisted
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.scavengerar.data.AppDatabase
import com.example.scavengerar.data.Item
import com.example.scavengerar.data.Level
import com.example.scavengerar.utilities.ITEMS_DATA_FILENAME
import com.example.scavengerar.utilities.LEVELS_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.logging.Level.INFO

@HiltWorker
class SeedDatabaseWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val levelsFilename = inputData.getString(LEVELS_FILENAME)
            val itemsFilename = inputData.getString(ITEMS_FILENAME)
            if (levelsFilename != null && itemsFilename != null) {
                applicationContext.assets.open(levelsFilename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val levels: List<Level> = Gson().fromJson(jsonReader, Level::class.java)
                        val items: List<Item> = Gson().fromJson(jsonReader, Item::class.java)
                        val database = AppDatabase.getInstance(applicationContext)
                        database.levelDao().insertAll(levels)
                        database.itemDao().insertAll(items)

                        val dbLevel = database.levelDao().getLevel(1)
                        val dbItem = database.itemDao().getItem(1)
                        dbLevel.collect { value ->  Log.i(TAG, "dbLevels - $value")}
                        dbItem.collect { value ->  Log.i(TAG, "dbLevels - $value")}
                        Log.i(TAG, "Databases seeded")
                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val LEVELS_FILENAME = LEVELS_DATA_FILENAME
        const val ITEMS_FILENAME = ITEMS_DATA_FILENAME
    }
}
