package com.app.pingu.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.ui.theme.mediumBlack12
import com.app.pingu.ui.theme.mediumBlack12Alpha50
import com.app.pingu.ui.theme.whisper
import com.app.pingu.ui.theme.white
import com.app.pingu.ui.theme.whiteSmoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun Tab(
    tabs: List<String>,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    selectedTab: (Int) -> Unit
) {
    TabRow(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        selectedTabIndex = selectedTabIndex,
        containerColor = whiteSmoke,
        indicator = {},
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            val selected = selectedTabIndex == index
            Tab(
                selected = selected,
                onClick = {
                    selectedTab.invoke(index)
                },
                interactionSource = NoRippleInteractionSource(),
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .then(
                        if (selected) {
                            Modifier.border(
                                1.dp, whisper, RoundedCornerShape(6.dp)
                            )
                        } else Modifier
                    )
                    .background(
                        if (selectedTabIndex == index) white else whiteSmoke
                    ),
                text = {
                    Text(
                        title,
                        style = if (selected) mediumBlack12 else mediumBlack12Alpha50,
                    )
                },
            )
        }
    }
}

private class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

@Preview(showBackground = true)
@Composable
private fun PhoneNumberInputPreview() {
    Tab(
        tabs = listOf("Tab1", "Tab2"),
        selectedTabIndex = 0,
        selectedTab = {}
    )
}