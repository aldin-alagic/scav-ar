package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds generated codes.
 */
@Entity(
    tableName = "code",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = CodeType::class,
            parentColumns = ["id"],
            childColumns = ["code_type_id"],
        ),
        androidx.room.ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
        ),
    ],
    indices = [Index("code_type_id"), Index("user_id")],
)

data class Code(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "code_type_id")  val codeTypeId: Int,
    @ColumnInfo(name = "user_id")  val userId: Int,
    @ColumnInfo(name = "value")  val value: String,
) {
    override fun toString() = value
}