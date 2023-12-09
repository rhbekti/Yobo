package com.rhbekti.yobo.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rhbekti.yobo.R
import com.rhbekti.yobo.ui.common.UserData
import com.rhbekti.yobo.ui.navigation.SettingItem
import com.rhbekti.yobo.ui.theme.YoboTheme

@Composable
fun ProfileScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        ) {
            Text(text = "User Profile", style = MaterialTheme.typography.titleLarge)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(24.dp)
        ) {
            AsyncImage(
                model = userData?.profilePictureUrl,
                contentDescription = userData?.username,
                modifier = modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = modifier.padding(horizontal = 8.dp))
            Text(text = userData?.username.toString(), style = MaterialTheme.typography.titleMedium)
        }

        val listSettingItem = listOf(
            SettingItem(stringResource(id = R.string.language)),
            SettingItem(stringResource(id = R.string.help)),
            SettingItem(stringResource(id = R.string.term)),
            SettingItem(stringResource(id = R.string.about))
        )

        SettingMenu(
            settingMenu = listSettingItem,
            modifier = modifier.padding(horizontal = 24.dp).fillMaxWidth()
        )

        TextButton(
            onClick = onSignOut,
            colors = ButtonDefaults.buttonColors(
                Color.Transparent
            ),
            modifier = modifier.padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_out), color = Color.Red)
        }
    }
}

@Composable
fun SettingMenu(
    settingMenu: List<SettingItem>,
    modifier: Modifier = Modifier
) {
    Column {
        settingMenu.map {
            Text(text = it.title, modifier = modifier.padding(vertical = 8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    YoboTheme {
        ProfileScreen(userData = UserData("123", "Rahman", "image"), onSignOut = {  })
    }
}