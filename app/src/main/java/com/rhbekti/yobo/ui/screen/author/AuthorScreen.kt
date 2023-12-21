package com.rhbekti.yobo.ui.screen.author

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.di.Injection
import com.rhbekti.yobo.model.Book
import com.rhbekti.yobo.ui.ViewModelFactory
import com.rhbekti.yobo.ui.component.AuthorItem

@Composable
fun AuthorScreen(
    authorViewModel: AuthorViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository()))
) {
    authorViewModel.authors.collectAsState(initial = Result.Loading).value.let { book ->
        when (book) {
            is Result.Loading -> {}
            is Result.Error -> {}
            is Result.Success -> {
                AuthorContent(book = book.data)
            }
        }
    }
}

@Composable
fun AuthorContent(
    book: List<BookItems>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(book) {
            AuthorItem(
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
