package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds code types
 */
@Entity(
    tableName = "code_type",
)
data class CodeType(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")  val name: String,
) {
    override fun toString() = name
}