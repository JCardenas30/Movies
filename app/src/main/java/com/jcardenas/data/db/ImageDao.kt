package com.jcardenas.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jcardenas.data.entities.ImageEntity

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: ImageEntity)

    @Delete
    suspend fun delete(image: ImageEntity)

    @Query("SELECT * FROM image")
    fun getImages(): LiveData<List<ImageEntity>>
}