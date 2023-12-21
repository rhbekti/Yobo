package com.rhbekti.yobo.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rhbekti.yobo.R
import com.rhbekti.yobo.ui.common.UserData
import com.rhbekti.yobo.ui.component.CardLoan
import com.rhbekti.yobo.ui.component.ContentSection
import com.rhbekti.yobo.ui.component.ContentSectionSubsection
import com.rhbekti.yobo.ui.screen.author.AuthorScreen
import com.rhbekti.yobo.ui.screen.book.BookScreen

@Composable
fun HomeScreen(
    userData: UserData?,
    navigateToDetail: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Header(userData = userData)

        CardLoan()

        ContentSection(
            title = "Top pick for you",
            content = { AuthorScreen() }
        )

        ContentSectionSubsection(
            title = "New this book",
            subTitle = "Discover what’s new on Yobo",
            content = { BookScreen(navigateToDetail = navigateToDetail) },
            modifier = Modifier.padding(vertical = 16.dp)
        )
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
