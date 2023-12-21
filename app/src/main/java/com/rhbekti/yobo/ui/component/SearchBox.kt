package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    query: String,
    onQueryChange: (String) -> Unit
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("search book")
        },
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

@Composable
fun BoxLabel(
    content: String
)
{
    Box(
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp))
            .padding(10.dp)
    ) {
        Text(
            text = content,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
fun BoxLabelPreview()
{
    BoxLabel(content = "chinese foods")
}