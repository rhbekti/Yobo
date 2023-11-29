package com.rhbekti.yobo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rhbekti.yobo.R
import com.rhbekti.yobo.ui.theme.YoboTheme

@Composable
fun SignInPage(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo_yobo),
            contentDescription = stringResource(
                id = R.string.logo_yobo
            ),
            modifier = modifier
                .padding(top = 80.dp)
                .fillMaxWidth()
                .height(192.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color.White),
            modifier = modifier
                .padding(bottom = 48.dp)
                .height(56.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = stringResource(id = R.string.sign_in_with_google),
                contentScale = ContentScale.Fit,
                modifier = modifier.size(24.dp).padding(end = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.sign_in_with_google),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInPagePreview() {
    YoboTheme {
        SignInPage()
    }
}