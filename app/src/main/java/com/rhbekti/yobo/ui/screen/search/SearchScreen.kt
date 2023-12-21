package com.rhbekti.yobo.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.ui.component.BookGrid
import com.rhbekti.yobo.ui.component.BoxLabel

@Composable
fun SearchScreen(
    navigateToDetail: (String) -> Unit
) {
    BookGrid(navigateToDetail = navigateToDetail)
}


@Composable
fun SearchResultBox(
    data: List<BookItems>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Your search results",
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(data) {
            BoxLabel(content = it.title.toString())
        }
    }
}

