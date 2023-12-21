package com.rhbekti.yobo.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.data.remote.response.BookItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailBookViewModel(private val repository: YoboRepository) : ViewModel() {

    private val _bookData: MutableStateFlow<Result<List<BookItems>>> =
        MutableStateFlow(Result.Loading)
    val bookData: StateFlow<Result<List<BookItems>>> get() = _bookData

    fun getDetailBookById(id: String) {
        viewModelScope.launch {
            repository.getBookById(id).catch {
                _bookData.value = Result.Error(it.message.toString())
            }.collect {
                _bookData.value = it
            }
        }
    }
}