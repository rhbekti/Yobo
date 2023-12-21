package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.util.DebugLogger
import com.rhbekti.yobo.model.Book

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.photoUrl)
                    .setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36")
                    .size(Size.ORIGINAL)
                    .build(),
                imageLoader = ImageLoader.Builder(LocalContext.current).logger(DebugLogger()).build()
            )

            Image(
                painter = painter,
                contentDescription = book.title,
            )

            Column {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = modifier.padding(8.dp)
                )

                Row {
                    Text(
                        text = book.year,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )

                    Text(
                        text = book.author,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                    )
                }
            }
        }
    }
}