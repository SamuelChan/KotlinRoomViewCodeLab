package com.samuelcychan.codelab

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class WordRepository(private  val wordDao: WordDao) {

    val words: Flow<List<Word>>  = wordDao.getOrderedWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}