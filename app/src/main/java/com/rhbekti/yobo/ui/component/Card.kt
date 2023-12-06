package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.loan_book),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = modifier.padding(top = 8.dp))
            Text(text = "0", style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))
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