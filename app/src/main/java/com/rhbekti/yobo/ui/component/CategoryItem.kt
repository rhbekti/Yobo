package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rhbekti.yobo.model.Category
import com.rhbekti.yobo.ui.theme.YoboTheme

@Composable
fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(Color.LightGray,shape = RoundedCornerShape(size = 16.dp)).padding(16.dp)
    ){
        Text(
            text = category.title,
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )
    }
}

@Composable
fun CircleLoad(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(40.dp)
            .height(20.dp)
            .background(Color.LightGray)
    )
}

@Preview(showBackground = true)
@Composable
fun CircleLoadPreview() {
    YoboTheme {
        CategoryItem(category = Category("Horror"))
    }
}

