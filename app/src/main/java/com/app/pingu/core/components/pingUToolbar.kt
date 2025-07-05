package com.app.pingu.core.components


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.R
import com.app.pingu.ui.theme.black
import com.app.pingu.ui.theme.boldBlack24
import com.app.pingu.ui.theme.white
import com.app.pingu.utils.extension.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SitToolbar(
    title: String,
    showBackButton: Boolean = true,
    onBackPressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = boldBlack24,
                maxLines = 1
            )
        },
        navigationIcon = {
            if (showBackButton) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .noRippleClickable {
                            onBackPressed.invoke()
                        }
                )
            } else {
                BackHandler(enabled = true) {}
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = white,
            titleContentColor = black,
            navigationIconContentColor = black
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SitToolbar(
        title = "Title",
        onBackPressed = {}
    )
}

