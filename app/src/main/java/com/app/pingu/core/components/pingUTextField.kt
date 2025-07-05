package com.app.pingu.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.pingu.R
import com.app.pingu.ui.theme.black
import com.app.pingu.ui.theme.lightGray
import com.app.pingu.ui.theme.mediumRed9
import com.app.pingu.ui.theme.red
import com.app.pingu.ui.theme.regularBlack15
import com.app.pingu.ui.theme.regularBlack9Alpha50
import com.app.pingu.ui.theme.white

@Composable
fun SitTextField(
    value: String,
    title: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    showTitle: Boolean = true,
    showError: Boolean = false,
    containerColor: Color = white,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    suffix: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = regularBlack15,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    placeholder: @Composable (() -> Unit)? = null,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(black),
    colors: TextFieldColors = run {

        TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    },
    shape: Shape = RoundedCornerShape(8.dp),
) {
    val borderColor = when {
        showError -> red
        value.isEmpty() -> lightGray
        else -> black
    }
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape)
                .background(containerColor)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = shape
                )
        ) {
            if (showTitle) {
                Text(
                    text = title,
                    style = regularBlack9Alpha50,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp)
                )
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                singleLine = singleLine,
                maxLines = maxLines,
                enabled = enabled,
                readOnly = readOnly,
                interactionSource = interactionSource,
                textStyle = textStyle,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                onTextLayout = onTextLayout,
                cursorBrush = cursorBrush,
                decorationBox = { innerTextField ->
                    GetDecorationBox(
                        value,
                        innerTextField,
                        enabled,
                        singleLine,
                        interactionSource,
                        placeholder,
                        leadingIcon,
                        trailingIcon,
                        shape,
                        colors,
                        suffix,
                        prefix
                    )
                }
            )
        }
        if (showError) {
            Text(
                text = stringResource(R.string.login_error_password),
                style = mediumRed9,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GetDecorationBox(
    text: String,
    innerTextField: @Composable () -> Unit,
    enabled: Boolean,
    singleLine: Boolean,
    interactionSource: MutableInteractionSource,
    placeholder: @Composable (() -> Unit)?,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
    shape: Shape,
    colors: TextFieldColors,
    suffix: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
) {
    TextFieldDefaults.DecorationBox(
        value = text,
        suffix = {
            if (suffix != null) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.TopEnd
                ) {
                    suffix()
                }
            }
        },
        prefix = {
            if (prefix != null) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    prefix()
                }
            }
        },
        innerTextField = {
            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
            }
        },
        enabled = enabled,
        singleLine = singleLine,
        visualTransformation = VisualTransformation.None,
        interactionSource = interactionSource,
        placeholder = {
            if (text.isEmpty() && placeholder != null) {
                Box(
                    modifier = Modifier.fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    placeholder()
                }
            }
        },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        shape = shape,
        colors = colors,
        contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
            top = 0.dp,
            bottom = 0.dp,
        ),
    )
}

@Preview
@Composable
private fun Preview() {
    SitTextField(
        modifier = Modifier
            .padding(8.dp)
            .height(48.dp),
        value = "123456",
        title = "Title",
        onValueChange = {},
    )
}