package com.ckarthickit.archroom.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val TABLE_WORDS = "words_table"
internal const val COLUMN_WORDS_WORD = "words_table"

@Entity(tableName = TABLE_WORDS) //Each entity class represents a table
data class Word(@NonNull @PrimaryKey @ColumnInfo(name= COLUMN_WORDS_WORD) val word: String)