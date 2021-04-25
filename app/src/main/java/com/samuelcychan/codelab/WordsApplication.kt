package com.samuelcychan.codelab

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    private val appScope = CoroutineScope(SupervisorJob())
    private val database by lazy { WordRoomDatabase.getDatabase(this, appScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}