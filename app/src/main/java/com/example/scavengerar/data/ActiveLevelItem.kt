package com.example.scavengerar.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

/**
 * Data class created for items of the currently active level
 */
data class ActiveLevelItem(
    @ColumnInfo(name = "id")  val id: Int,
    @ColumnInfo(name = "name")  val name: String,
    @ColumnInfo(name = "description")  val description: String,
    @ColumnInfo(name = "image")  val imageUrl: String,
    @ColumnInfo(name = "completed")  val completed: Boolean,
)