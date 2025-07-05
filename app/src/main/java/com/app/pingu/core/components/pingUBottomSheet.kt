package com.app.pingu.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.ui.theme.lightGray
import com.app.pingu.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SitBottomSheet(
    onDismissRequest: () -> Unit,
    isCancelable: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            isCancelable
        },
    )
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
        containerColor = white,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .width(48.dp)
                    .height(5.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(lightGray)
            )
        }
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SitBottomSheet(
        onDismissRequest = {},
        content = {
            Text("Test")
        }
    )
}
