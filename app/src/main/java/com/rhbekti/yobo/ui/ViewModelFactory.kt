package com.rhbekti.yobo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhbekti.yobo.data.YoboRepository
import com.rhbekti.yobo.ui.screen.book.BookViewModel
import com.rhbekti.yobo.ui.screen.author.AuthorViewModel
import com.rhbekti.yobo.ui.screen.detail.DetailBookViewModel
import com.rhbekti.yobo.ui.screen.search.SearchViewModel

class ViewModelFactory(private val repository: YoboRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthorViewModel::class.java)) {
            return AuthorViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailBookViewModel::class.java)) {
            return DetailBookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}