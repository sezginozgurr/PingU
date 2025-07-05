package com.app.pingu.presentation.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.R
import com.app.pingu.core.components.PrimaryButton
import com.app.pingu.core.components.SecondaryButton
import com.app.pingu.core.components.SitScaffold
import com.app.pingu.domain.model.result.ResultScreenType
import com.app.pingu.domain.model.result.ResultUiModel
import com.app.pingu.ui.theme.mediumBlack16
import com.app.pingu.ui.theme.regularBlack12Alpha50
import com.app.pingu.ui.theme.regularBlack42Alpha50
import com.app.pingu.ui.theme.whisper
import com.app.pingu.ui.theme.white

@Composable
fun ResultScreenRoute(
    resultUiModel: ResultUiModel,
    onNavigateToDashboard: () -> Unit,
) {
    ResultScreen(
        resultUiModel,
        onNavigateToDashboard = onNavigateToDashboard
    )
}

@Composable
private fun ResultScreen(
    resultUiModel: ResultUiModel,
    onNavigateToDashboard: () -> Unit,
) {
    val resultType = resultUiModel.status
    val title = getResultTitle(resultType)

    SitScaffold(
        title = title,
        showBackButton = false,
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(white)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            when (resultType) {
                ResultScreenType.SUCCESS -> {
                    ResultContentView(
                        description = "",
                        imageRes = painterResource(R.drawable.ic_launcher_background),
                        amountTitle = "",
                        amountValue = resultUiModel.amount
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    ResultPrimaryButton(
                        buttonName = "Tamams",
                        Modifier.padding(vertical = 24.dp),
                        onClick = onNavigateToDashboard

                    )
                }
            }
        }
    }
}

@Composable
private fun getResultTitle(resultType: ResultScreenType): String {
    return when (resultType) {
        ResultScreenType.SUCCESS -> {
            "ipsum"
        }
    }
}

@Composable
private fun ResultContentView(
    description: String,
    imageRes: Painter,
    amountTitle: String,
    amountValue: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = description,
            modifier = Modifier.fillMaxWidth(),
            style = regularBlack12Alpha50.copy(textAlign = TextAlign.Center)
        )
        Image(
            painter = imageRes,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 96.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$",
                style = regularBlack12Alpha50
            )
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(width = 16.dp, height = 12.dp)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .width(212.dp),
            thickness = 1.dp,
            color = whisper
        )
        Text(
            text = amountTitle,
            style = mediumBlack16
        )
        Text(
            text = amountValue,
            style = regularBlack42Alpha50
        )
    }
}

@Composable
private fun ResultPrimaryButton(
    buttonName: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    PrimaryButton(
        text = buttonName,
        modifier = modifier,
        actionClickListener = onClick
    )
}

@Composable
private fun ResultSecondaryButton(
    buttonName: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    SecondaryButton(
        text = buttonName,
        modifier = modifier,
        actionClickListener = onClick
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ResultScreen(
        resultUiModel = ResultUiModel(
            status = ResultScreenType.SUCCESS,
            successMessage = "ipsum"
        ),
        onNavigateToDashboard = {}
    )
}