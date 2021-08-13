package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds levels
 */
@Entity(
    tableName = "level",
)
data class Level(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "description")  val description: String,
    @ColumnInfo(name = "image")  val imageUrl: String,
    @ColumnInfo(name = "difficulty")  val difficulty: Int,
    @ColumnInfo(name = "created_at")  val createdAt: Long,
) {
    override fun toString() = name
}