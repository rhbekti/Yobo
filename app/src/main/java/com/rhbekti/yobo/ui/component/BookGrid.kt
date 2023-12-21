package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.di.Injection
import com.rhbekti.yobo.model.Book
import com.rhbekti.yobo.ui.ViewModelFactory
import com.rhbekti.yobo.ui.screen.search.SearchResultBox
import com.rhbekti.yobo.ui.screen.search.SearchViewModel

@Composable
fun BookGrid(
    searchViewModel: SearchViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (String) -> Unit
) {
    val query by searchViewModel.query.collectAsState()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SearchBox(
            query = query,
            onQueryChange = searchViewModel::searchBookByTitle
        )

        searchViewModel.bookList.collectAsState(initial = Result.Loading).value.let { result ->
            when (result) {
                is Result.Loading -> {

                }

                is Result.Error -> {

                }

                is Result.Success -> {
                    BookContentGrid(book = result.data, navigateToDetail = navigateToDetail)
                }
            }
        }
    }
}

@Composable
fun BookContentGrid(
    book: List<BookItems>,
    navigateToDetail: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            SearchResultBox(data = book)
        }

        item {
            Text(
                text = "Explore your book",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        items(book) {
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