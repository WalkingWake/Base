package dev.lazygarde.multi.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes

data class CustomTheme(
    val name: String,
    @StyleRes val theme: Int,
    @DrawableRes val backgroundImage: Int,
    val primaryColor: Int,
    val backgroundColor: Int,
    @DrawableRes val previewImage: Int,
    val isDark: Boolean = false,
)