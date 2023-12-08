package com.rhbekti.yobo.ui.screen.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.data.remote.response.BookItems
import kotlinx.coroutines.flow.MutableStateFlow
import com.rhbekti.yobo.data.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: YoboRepository) : ViewModel() {

    private val _books: MutableStateFlow<Result<List<BookItems>>> = MutableStateFlow(Result.Loading)

    val books: StateFlow<Result<List<BookItems>>> get() = _books

    init {
        getAllBooks()
    }

    private fun getAllBooks() {
        viewModelScope.launch {
            repository.getAllBooks()
                .collect {
                    _books.value = it
                }
        }
    }
}