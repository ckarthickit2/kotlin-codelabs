package com.ckarthickit.archroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ckarthickit.archroom.data.db.WordsDatabase
import com.ckarthickit.archroom.data.entity.Word
import com.ckarthickit.archroom.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordsViewModel(application: Application) : AndroidViewModel(application) {
    private val  repository: WordRepository
    val allWords : LiveData<List<Word>>

    init {
        val wordsDao = WordsDatabase.getInstance(application, viewModelScope).wordDao()
        repository = WordRepository(wordDao = wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}