package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds items.
 */
@Entity(
    tableName = "item",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["level_id"],
        ),
    ],
    indices = [Index("level_id")],
)
data class Item(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "level_id")  val levelId: Int,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "description")  val description: String,
    @ColumnInfo(name = "image")  val imageUrl: String,
    @ColumnInfo(name = "created_at")  val createdAt: Long,
) {
    override fun toString() = name
}