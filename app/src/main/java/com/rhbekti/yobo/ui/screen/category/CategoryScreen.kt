package com.rhbekti.yobo.ui.screen.category

import android.util.Log
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
import com.rhbekti.yobo.data.remote.response.CategoryItems
import com.rhbekti.yobo.di.Injection
import com.rhbekti.yobo.model.Category
import com.rhbekti.yobo.ui.ViewModelFactory
import com.rhbekti.yobo.ui.component.CategoryItem
import com.rhbekti.yobo.ui.component.CircleLoad

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
) {
    viewModel.categories.collectAsState(initial = Result.Loading).value.let { category ->
        when (category) {
            is Result.Loading -> {
                CircleLoad()
            }

            is Result.Error -> {
            }

            is Result.Success -> {
                Log.d("Cat Data: ", category.data.toString())
                CategoryContent(category.data)
            }
        }
    }
}

@Composable
fun CategoryContent(
    category: List<CategoryItems>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(category) {
            CategoryItem(category = Category(it.photoUrl.toString(), it.title.toString()))
        }
    }
}
