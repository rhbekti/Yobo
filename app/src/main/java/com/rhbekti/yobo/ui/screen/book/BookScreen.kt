package com.rhbekti.yobo.ui.screen.book

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rhbekti.yobo.di.Injection
import com.rhbekti.yobo.ui.ViewModelFactory
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.model.Book
import com.rhbekti.yobo.ui.component.BookItem

@Composable
fun BookScreen(
    bookViewModel: BookViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
) {
    bookViewModel.books.collectAsState(initial = Result.Loading).value.let { book ->
        when (book) {
            is Result.Loading -> {}
            is Result.Error -> {}
            is Result.Success -> {
                Log.d("bookdata", book.data.toString())
                BookContent(book.data)
            }
        }
    }
}

@Composable
fun BookContent(
    book: List<BookItems>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(book) {
            BookItem(
                book = Book(
                    it.isbn.toString(),
                    it.title.toString(),
                    it.author.toString(),
                    it.year.toString(),
                    it.publisher.toString(),
                    it.photoUrl?.m.toString()
                )
            )
        }
    }
}
