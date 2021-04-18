package com.example.androidwithkotlin.room

import android.database.Cursor
import androidx.room.*
import com.example.testcinema.DataClasses.Cinema

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM FavoriteEntity")
    fun all(): List<FavoriteEntity>

    @Query("SELECT * FROM FavoriteEntity WHERE title LIKE :cinema")
    fun getDataByWord(cinema: String): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FavoriteEntity)

    @Update
    fun update(entity: FavoriteEntity)

    @Delete
    fun delete(entity: FavoriteEntity)

    @Query("DELETE FROM FavoriteEntity WHERE id = :id")
    fun deleteById(id: Long)

//    @Query("SELECT id, city, temperature FROM HistoryEntity")
//    fun getHistoryCursor(): Cursor

//    @Query("SELECT id, city, temperature FROM HistoryEntity WHERE id = :id")
//    fun getHistoryCursor(id: Long): Cursor
}
