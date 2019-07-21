package com.ckarthickit.archroom.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.ckarthickit.archroom.data.dao.WordDao
import com.ckarthickit.archroom.data.entity.Word

class WordRepository(private val wordDao: WordDao) {
    val allWords : LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

}