package com.rhbekti.yobo.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ContentSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        SectionText(title, modifier)
        content()
    }
}

@Composable
fun ContentSectionSubsection(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        SectionText(title, modifier)
        SubSectionText(subTitle = subTitle)
        content()
    }
}