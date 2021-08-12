package com.example.scavengerar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Data class that represents a db table that holds users
 */
@Entity(
    tableName = "user",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Role::class,
            parentColumns = ["id"],
            childColumns = ["role_id"],
        ),
    ],
    indices = [Index("role_id")],
)
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "role_id")  val roleId: Int,
    @ColumnInfo(name = "premium") val premium: Boolean  = false,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "username")  val username: String,
    @ColumnInfo(name = "full_name")  val fullName: String,
    @ColumnInfo(name = "about")  val about: String,
    @ColumnInfo(name = "profile_photo")  val profilePhotoUrl: String,
    @ColumnInfo(name = "password")  val password: String,
    @ColumnInfo(name = "activated") val activated: Boolean = false,
    @ColumnInfo(name = "deleted") val deleted: Boolean  = false,
    @ColumnInfo(name = "newsletter") val newsletter: Boolean  = false,
    @ColumnInfo(name = "login_attempts") val loginAttempts: Int = 0,
    @ColumnInfo(name = "created_at")  val createdAt: Long,
) {
    override fun toString() = fullName
}