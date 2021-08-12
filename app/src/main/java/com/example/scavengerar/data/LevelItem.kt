package com.example.scavengerar.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

/**
 * Data class that represents a db table that holds items found on certain user level.
 */
@Entity(
    tableName = "level_item",
    primaryKeys = ["item_id", "user_level_id"],
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Item::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
        ),
        androidx.room.ForeignKey(
            entity = UserLevel::class,
            parentColumns = ["id"],
            childColumns = ["user_level_id"],
        ),
    ],
    indices = [Index("item_id"),Index("user_level_id")],
)
data class LevelItem(
    @NonNull
    @ColumnInfo(name = "item_id")  val itemId: Int,
    @NonNull
    @ColumnInfo(name = "user_level_id")  val userLevelId: Int,
    @ColumnInfo(name = "created_at")  val createdAt: Long,
)