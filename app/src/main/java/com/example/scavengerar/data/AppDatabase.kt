package com.example.scavengerar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.scavengerar.utilities.DATABASE_NAME
import com.example.scavengerar.utilities.ITEMS_DATA_FILENAME
import com.example.scavengerar.utilities.LEVELS_DATA_FILENAME
import com.example.scavengerar.workers.SeedDatabaseWorker
import com.example.scavengerar.workers.SeedDatabaseWorker.Companion.LEVELS_FILENAME
import com.example.scavengerar.workers.SeedDatabaseWorker.Companion.ITEMS_FILENAME

/**
 * Database for storing app data
 */
@Database(
    entities = [Role::class,
                User::class,
                CodeType::class,
                Code::class,
                Level::class,
                Item::class,
                UserLevel::class,
                LevelItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun levelDao(): LevelDao
    abstract fun itemDao(): ItemDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        /**
         * Create and pre-populate the database.
         */
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                       .addCallback(
                           object : RoomDatabase.Callback() {
                               override fun onCreate(db: SupportSQLiteDatabase) {
                                   super.onCreate(db)
                                   val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                                 .setInputData(workDataOf(LEVELS_FILENAME to LEVELS_DATA_FILENAME,
                                                                                ITEMS_FILENAME to ITEMS_DATA_FILENAME))
                                                 .build()
                                   WorkManager.getInstance(context).enqueue(request)
                               }
                           }
                       )
                       .build()
        }
    }
}