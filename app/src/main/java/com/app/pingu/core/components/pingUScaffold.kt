package com.app.pingu.core.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun SitScaffold(
    title: String = "",
    showBackButton: Boolean = true,
    onBackPressed: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            if (title.isNotEmpty()) {
                SitToolbar(
                    title = title,
                    showBackButton = showBackButton,
                    onBackPressed = onBackPressed
                )
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}
