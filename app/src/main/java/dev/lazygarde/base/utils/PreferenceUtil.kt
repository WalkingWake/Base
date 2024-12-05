package dev.lazygarde.base.utils

import android.content.SharedPreferences
import dev.lazygarde.flow.preferences.FlowSharedPreferences
import dev.lazygarde.lazy.preference.LazyPreference
import javax.inject.Inject

class PreferenceUtil @Inject constructor(
    preference: SharedPreferences,
    editor: SharedPreferences.Editor,
    flowPreferences: FlowSharedPreferences
) : LazyPreference(preference, editor) {

    var isFirstRun by booleanPref("isFirstRun", true)
    var theme by intPref("theme", 0)

}