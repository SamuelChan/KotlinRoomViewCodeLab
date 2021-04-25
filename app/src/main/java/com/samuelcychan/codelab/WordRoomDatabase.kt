package com.samuelcychan.codelab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): WordRoomDatabase {
            return INSTANCE?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database")
                .addCallback(WordDatabaseCallback(scope))
                .build().also { INSTANCE = it }
            }
        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                database ->
                scope.launch { populateFromDatabase(database.wordDao()) }
            }
        }
        suspend fun populateFromDatabase(wordDao: WordDao) {
            with(wordDao) {
                delete()
                // TODO: 2021/4/24
                insert(Word("testWord_1"))
                insert(Word("testWord_2"))
            }
        }
    }
}

