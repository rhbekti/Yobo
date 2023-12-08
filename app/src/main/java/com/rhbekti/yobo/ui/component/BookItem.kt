package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rhbekti.yobo.model.Book

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
        ) {
            AsyncImage(
                model = "https://perpustakaan.smkn1kbm.sch.id/lib/minigalnano/createthumb.php?filename=images/docs/AZZAMINE.jpeg.jpeg&width=200",
                contentDescription = book.title
            )
            Text(text = book.title, style = MaterialTheme.typography.labelMedium)
        }
    }
}