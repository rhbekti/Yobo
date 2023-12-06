package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rhbekti.yobo.model.Category
import com.rhbekti.yobo.ui.theme.YoboTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            model = category.photoUrl,
            contentDescription = category.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
        Text(
            text = category.title,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
        )
    }
}

@Composable
fun CircleLoad(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.shimmer()
    ) {
        Box(
            modifier = modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
        Spacer(modifier = modifier.padding(vertical = 8.dp))
        Box(
            modifier = modifier
                .width(40.dp)
                .height(10.dp)
                .background(Color.LightGray)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircleLoadPreview() {
    YoboTheme {
        CircleLoad()
    }
}

