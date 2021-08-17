package com.example.scavengerar.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import dagger.assisted.AssistedInject
import dagger.assisted.Assisted
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.scavengerar.data.*
import com.example.scavengerar.utilities.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SeedDatabaseWorker @AssistedInject constructor (
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val levelsFilename = LEVELS_DATA_FILENAME
        val itemsFilename = ITEMS_DATA_FILENAME
        val rolesFilename = ROLES_DATA_FILENAME
        val usersFilename = USERS_DATA_FILENAME
        val userLevelsFilename = USER_LEVELS_DATA_FILENAME

        try {
            val database = AppDatabase.getInstance(applicationContext)

            applicationContext.assets.open(levelsFilename).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val levelType = object : TypeToken<List<Level>>() {}.type
                    val levels: List<Level> = Gson().fromJson(jsonReader, levelType)
                    database.levelDao().insertAll(levels)
                }
            }
            applicationContext.assets.open(itemsFilename).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val itemType = object : TypeToken<List<Item>>() {}.type
                    val items: List<Item> = Gson().fromJson(jsonReader, itemType)
                    database.itemDao().insertAll(items)
                }
            }
            applicationContext.assets.open(rolesFilename).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val roleType = object : TypeToken<List<Role>>() {}.type
                    val roles: List<Role> = Gson().fromJson(jsonReader, roleType)
                    database.roleDao().insertAll(roles)
                }
            }
            applicationContext.assets.open(usersFilename).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val userType = object : TypeToken<List<User>>() {}.type
                    val users: List<User> = Gson().fromJson(jsonReader, userType)
                    database.userDao().insertAll(users)
                }
            }
            applicationContext.assets.open(userLevelsFilename).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val userLevelType = object : TypeToken<List<UserLevel>>() {}.type
                    val userLevels: List<UserLevel> = Gson().fromJson(jsonReader, userLevelType)
                    database.userLevelDao().insertAll(userLevels)
                }
            }
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}
