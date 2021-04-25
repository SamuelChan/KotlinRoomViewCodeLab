package com.samuelcychan.codelab

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {
    val words = repository.words.asLiveData()

    fun insert(word: Word) = viewModelScope.launch { repository.insert(word) }
}

class WordViewModelFactory(private val repository: WordRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}