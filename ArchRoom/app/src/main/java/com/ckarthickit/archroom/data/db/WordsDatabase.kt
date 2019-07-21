package com.ckarthickit.archroom.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ckarthickit.archroom.data.dao.WordDao
import com.ckarthickit.archroom.data.entity.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "WordsDatabase"
internal const val WORDS_DATABASE = "Word_database"

@Database(entities = [Word::class], version = 1, exportSchema = false)
public abstract class WordsDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {

        @Volatile
        private var INSTANCE: WordsDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): WordsDatabase {
            Log.w(TAG, "getInstance")
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext, WordsDatabase::class.java,
                        WORDS_DATABASE
                    ).addCallback(WordDatabaseCallback(scope = scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }


    private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {


        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Log.w(TAG, "onCreate")
        }
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            Log.w(TAG, "onOpen")
            INSTANCE?.let { it ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(it.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            Log.w(TAG, "populateDatabase")
            wordDao.deleteAll()
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World")
            wordDao.insert(word)
        }


    }
}