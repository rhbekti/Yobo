package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rhbekti.yobo.model.Book

@Composable
fun AuthorItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color.LightGray,shape = RoundedCornerShape(size = 16.dp)).padding(16.dp)
    ){
        Text(
            text = book.author,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )
    }
}



