package co.edu.uan.android.finalexample418.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.edu.uan.android.finalexample418.entities.CatEntity

@Dao
interface CatDao {
    @Query("SELECT * FROM cats")
    suspend fun findAll(): List<CatEntity>

    @Query("SELECT * FROM cats WHERE id = :id")
    suspend fun findById(id: String) :CatEntity

    @Insert
    suspend fun save(c: CatEntity)
}