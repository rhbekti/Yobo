package com.rhbekti.yobo.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.util.DebugLogger
import com.rhbekti.yobo.data.Result
import com.rhbekti.yobo.data.remote.response.BookItems
import com.rhbekti.yobo.di.Injection
import com.rhbekti.yobo.ui.ViewModelFactory

@Composable
fun DetailScreen(
    bookId: String,
    navigateBack: () -> Unit,
    viewModel: DetailBookViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    )
) {
    LaunchedEffect(bookId) {
        viewModel.getDetailBookById(bookId)
    }

    viewModel.bookData.collectAsState(initial = Result.Loading).value.let { result ->
        when (result) {
            is Result.Loading -> {}
            is Result.Error -> {}
            is Result.Success -> {
                DetailBookDetail(
                    data = result.data[0],
                    navigateOnBack = navigateBack
                )
            }
        }
    }
}

@Composable
fun DetailBookDetail(
    data: BookItems,
    navigateOnBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Header(navigateOnBack = navigateOnBack)

        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.photoUrl?.l)
                .setHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"
                )
                .size(Size.ORIGINAL)
                .build(),
            imageLoader = ImageLoader.Builder(LocalContext.current).logger(DebugLogger()).build()
        )

        Image(
            painter = painter,
            contentDescription = data.title,
            modifier = modifier.width(100.dp)
        )

        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "Title",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )

            Text(
                text = data.title.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "Author",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )

            Text(
                text = data.author.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "Year",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )

            Text(
                text = data.year.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = "Publisher",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(16.dp)
            )

            Text(
                text = data.publisher.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier.padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    navigateOnBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = modifier.clickable { navigateOnBack() }
            )
        },
        title = {
            Text("Detail")
        }
    )
}
