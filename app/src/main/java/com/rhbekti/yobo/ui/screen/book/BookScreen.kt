package com.rhbekti.yobo.ui.screen.book

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.di.Injection
import com.rhbekti.yobo.model.Book
import com.rhbekti.yobo.ui.ViewModelFactory
import com.rhbekti.yobo.ui.component.BookItem
import com.valentinilk.shimmer.shimmer

@Composable
fun BookScreen(
    bookViewModel: BookViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (String) -> Unit
) {
    bookViewModel.books.collectAsState(initial = Result.Loading).value.let { book ->
        when (book) {
            is Result.Loading -> {
                BookLoadingContent()
            }

            is Result.Error -> {}
            is Result.Success -> {
                BookContent(book.data, navigateToDetail = navigateToDetail)
            }
        }
    }
}

@Composable
fun BookLoadingContent() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .shimmer(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun BookContent(
    book: List<BookItems>,
    navigateToDetail: (String) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(book.take(10)) {
            BookItem(
                book = Book(
                    it.isbn.toString(),
                    it.title.toString(),
                    it.author.toString(),
                    it.year.toString(),
                    it.publisher.toString(),
                    it.photoUrl?.m.toString()
                ),
                modifier = Modifier.clickable {
                    navigateToDetail(it.isbn.toString())
                }
            )
        }
    }
}

