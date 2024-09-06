package dev.lazygarde.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import dev.lazygarde.base.databinding.ActivityMainBinding
import dev.lazygarde.base.BaseActivity
import dev.lazygarde.base.utils.PreferenceUtil
import dev.lazygarde.multi.theme.MultiThemeHelper
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var preferences: PreferenceUtil

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        preferences.isFirstRun = false

        binding.tvMain.setOnClickListener {
            MultiThemeHelper.selectTheme(this)
        }
    }
}