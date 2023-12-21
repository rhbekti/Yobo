package com.rhbekti.yobo.ui.screen.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.data.remote.response.BookItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthorViewModel(private val repository: YoboRepository) : ViewModel() {

    private val _authors: MutableStateFlow<Result<List<BookItems>>> =
        MutableStateFlow(Result.Loading)
    val authors: StateFlow<Result<List<BookItems>>> get() = _authors

    init {
        getAllCategory()
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            repository.getBookByAuthor().collect {
                _authors.value = it
            }
        }
    }
}