package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds levels of a certain user.
 */
@Entity(
    tableName = "user_level",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
        ),
        androidx.room.ForeignKey(
            entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["level_id"],
        ),
    ],
    indices = [Index("user_id"),Index("level_id")],
)
data class UserLevel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "user_id")  val userId: Int,
    @ColumnInfo(name = "level_id")  val levelId: Int,
    @ColumnInfo(name = "completed")  val description: Boolean,
    @ColumnInfo(name = "created_at")  val createdAt: Long,
)