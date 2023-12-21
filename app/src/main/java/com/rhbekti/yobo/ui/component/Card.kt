package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rhbekti.yobo.R
import com.rhbekti.yobo.ui.theme.YoboTheme

@Composable
fun CardLoan(
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 16.dp).fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.loan_book),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    modifier = modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 0.dp)
                )
                Spacer(modifier = modifier.padding(top = 8.dp))
                Text(
                    text = "0",
                    style = MaterialTheme
                        .typography
                        .headlineMedium
                        .copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    modifier = modifier.padding(horizontal = 16.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.help),
                    contentDescription = null,
                    modifier = modifier.size(32.dp)
                )
                Text(text = "Help", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardLoanPreview() {
    YoboTheme {
        CardLoan()
    }
}