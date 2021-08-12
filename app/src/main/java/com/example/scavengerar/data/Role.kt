package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds user roles
 */
@Entity(
    tableName = "role",
)
data class Role(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")  val name: String,
) {
    override fun toString() = name
}