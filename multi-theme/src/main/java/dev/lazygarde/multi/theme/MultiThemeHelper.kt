package dev.lazygarde.multi.theme

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.view.WindowInsetsControllerCompat

object MultiThemeHelper {
    fun selectTheme(context: Context) {
        context.startActivity(
            Intent(
                context,
                SelectThemeActivity::class.java
            )
        )
    }

//    fun setUpActivityWithSavedTheme(activity: Activity) {
//        val savedTheme = getSavedTheme(activity)
//        activity.setTheme(savedTheme.theme)
//        WindowInsetsControllerCompat(activity.window, activity.window.decorView)
//            .isAppearanceLightStatusBars = !savedTheme.isDark
//    }
//
//    fun getSavedTheme(context: Context): CustomTheme {
//        val preferences = context.getSharedPreferences(Const.THEME_PREFERENCE, Context.MODE_PRIVATE)
//        val theme = preferences.getInt(Const.SAVED_THEME, R.style.Theme_blue_enchantress)
//        Log.d("MultiThemeHelper", "getSavedTheme: $theme")
//        return getCustomThemes().find { it.theme == theme } ?: getCustomThemes()[0]
//    }
//
//    internal fun saveTheme(context: Context, customTheme: CustomTheme) {
//        val preferences = context.getSharedPreferences(Const.THEME_PREFERENCE, Context.MODE_PRIVATE)
//        preferences.edit().putInt(Const.SAVED_THEME, customTheme.theme).apply()
//    }

    fun getCustomThemes(): List<CustomTheme> {
        return listOf(
            CustomTheme(
                "blue_enchantress",
                R.style.Theme_blue_enchantress,
                R.drawable.image_background_theme_blue_enchantress,
                Color.parseColor("#335EE1"),
                Color.parseColor("#E1EEF8"),
                R.drawable.image_preview_theme_blue_enchantress,
            ),
            CustomTheme(
                "grass",
                R.style.Theme_grass,
                R.drawable.image_background_theme_grass,
                Color.parseColor("#79C85B"),
                Color.parseColor("#F3F6DA"),
                R.drawable.image_preview_theme_grass,
            ),
            CustomTheme(
                "autumn",
                R.style.Theme_autumn,
                R.drawable.image_background_theme_autumn,
                Color.parseColor("#FF9C23"),
                Color.parseColor("#FBF4E1"),
                R.drawable.image_preview_theme_autumn,
            ),
            CustomTheme(
                "guru_dark",
                R.style.Theme_guru_dark,
                R.drawable.image_background_theme_guru_dark,
                Color.parseColor("#0085FF"),
                Color.parseColor("#001520"),
                R.drawable.image_preview_theme_guru_dark,
                true
            ),
            CustomTheme(
                "pink_cyan",
                R.style.Theme_pink_cyan,
                R.drawable.image_background_theme_pink_cyan,
                Color.parseColor("#FF6DA2"),
                Color.parseColor("#FDD7E3"),
                R.drawable.image_preview_theme_pink_cyan
            ),
            CustomTheme(
                "star_desert",
                R.style.Theme_star_desert,
                R.drawable.image_background_theme_star_desert,
                Color.parseColor("#FE8493"),
                Color.parseColor("#F4ECF1"),
                R.drawable.image_preview_theme_star_desert
            )
        )
    }
}