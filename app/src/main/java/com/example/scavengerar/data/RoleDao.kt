package com.example.scavengerar.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the Role class.
 */
@Dao
interface RoleDao {
    @Query("SELECT * FROM role")
    fun getRoles(): Flow<List<Role>>

    @Query("SELECT * FROM role WHERE id = :roleId")
    fun getRole(roleId: Int): Flow<Role>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(roles: List<Role>)
}
