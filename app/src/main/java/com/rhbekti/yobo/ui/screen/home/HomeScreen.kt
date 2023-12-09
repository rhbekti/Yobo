package com.rhbekti.yobo.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rhbekti.yobo.R
import com.rhbekti.yobo.ui.common.UserData
import com.rhbekti.yobo.ui.component.CardLoan
import com.rhbekti.yobo.ui.component.ContentSection
import com.rhbekti.yobo.ui.screen.book.BookScreen
import com.rhbekti.yobo.ui.screen.category.CategoryScreen
import com.rhbekti.yobo.ui.theme.YoboTheme

@Composable
fun HomeScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Header(userData = userData)

        CardLoan()

        ContentSection(
            title = stringResource(id = R.string.categories),
            content = { CategoryScreen() },
            modifier = modifier.padding(vertical = 8.dp)
        )

        ContentSection(
            title = "New Books",
            content = { BookScreen() }
        )

        Button(onClick = onSignOut) {
            Text(text = stringResource(id = R.string.sign_out))
        }
    }
}


@Composable
private fun Header(
    userData: UserData?,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        Column {
            Text(text = stringResource(id = R.string.hello))
            if (userData?.username != null) {
                Text(
                    text = userData.username,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        AsyncImage(
            model = userData?.profilePictureUrl,
            contentDescription = userData?.username,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    YoboTheme {
        HomeScreen(
            userData = UserData(
                "123",
                "Rahman P",
                "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?q=80&w=1480&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            ),
            onSignOut = {}
        )
    }
}