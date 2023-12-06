package com.rhbekti.yobo.ui.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.data.remote.response.CategoryItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: YoboRepository) : ViewModel() {

    private val _categories: MutableStateFlow<Result<List<CategoryItems>>> =
        MutableStateFlow(Result.Loading)
    val categories: StateFlow<Result<List<CategoryItems>>> get() = _categories

    init {
        getAllCategory()
    }

    private fun getAllCategory() {
        viewModelScope.launch {
            repository.getAllCategories().collect {
                _categories.value = it
            }
        }
    }
}