package com.rhbekti.yobo.ui.screen.search

import androidx.lifecycle.ViewModel
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.model.BookRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

class SearchViewModel(private val repository: YoboRepository) : ViewModel() {
    private val _query: MutableStateFlow<String> = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    @OptIn(ExperimentalCoroutinesApi::class)
    val bookList: Flow<Result<List<BookItems>>> = _query.flatMapLatest {
        val bookRequest = BookRequest(listOf(it))
        repository.getAllBooks(bookRequest)
    }

    fun searchBookByTitle(q: String) {
        _query.value = q
    }
}