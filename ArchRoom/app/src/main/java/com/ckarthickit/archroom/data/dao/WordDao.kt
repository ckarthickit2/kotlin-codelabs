package com.ckarthickit.archroom.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ckarthickit.archroom.data.entity.COLUMN_WORDS_WORD
import com.ckarthickit.archroom.data.entity.TABLE_WORDS
import com.ckarthickit.archroom.data.entity.Word

@Dao
interface WordDao {

    @Query("SELECT * from $TABLE_WORDS ORDER BY $COLUMN_WORDS_WORD ASC")
    fun getAllWords(): LiveData<List<Word>>

    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Query("DELETE FROM $TABLE_WORDS")
    suspend fun deleteAll()

}