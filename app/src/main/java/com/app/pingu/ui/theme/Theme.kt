package com.app.pingu.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.pingu.R

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun PingUTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val pingUDMSansFamily = FontFamily(
    Font(R.font.pingu_regular, FontWeight.Normal),
    Font(R.font.pingu_medium, FontWeight.Medium),
    Font(R.font.pingu_semibold, FontWeight.SemiBold),
    Font(R.font.pingu_bold, FontWeight.Bold),
)

val regularBlack9 = TextStyle(
    color = black,
    fontSize = 9.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal
)
val regularBlack9Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 9.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal
)
val regularBlack15 = TextStyle(
    color = black,
    fontSize = 15.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal
)
val regularLightGray15 = TextStyle(
    color = lightGray,
    fontSize = 15.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal
)
val regularLightGray20 = TextStyle(
    color = lightGray,
    fontSize = 20.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal
)
val regularBlack12 = TextStyle(
    color = black,
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack16Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 16.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack14Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 14.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack12Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularWhite24 = TextStyle(
    color = white,
    fontSize = 24.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack24 = TextStyle(
    color = black,
    fontSize = 24.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack32 = TextStyle(
    color = black,
    fontSize = 32.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack32Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 32.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)
val regularBlack42Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 42.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Normal,
)

//Medium  W500
val mediumWhite16 = TextStyle(
    color = white,
    fontSize = 16.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumBlack15 = TextStyle(
    color = black,
    fontSize = 15.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumBlack16 = TextStyle(
    color = black,
    fontSize = 16.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumRed9 = TextStyle(
    color = red,
    fontSize = 9.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumBlack9 = TextStyle(
    color = black,
    fontSize = 9.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumDarkGray9 = TextStyle(
    color = darkGray,
    fontSize = 9.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumLimeGreen12 = TextStyle(
    color = limeGreen,
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumRoman12 = TextStyle(
    color = roman,
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumBlack12 = TextStyle(
    color = black,
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumBlack12Alpha50 = TextStyle(
    color = black.copy(alpha = 0.5f),
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)
val mediumDimGray12 = TextStyle(
    color = dimGray,
    fontSize = 12.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)

val mediumBlack32 = TextStyle(
    color = black,
    fontSize = 32.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Medium
)


//Bold  W700
val boldBlack24 = TextStyle(
    color = black,
    fontSize = 24.sp,
    fontFamily = pingUDMSansFamily,
    fontWeight = FontWeight.Bold
)