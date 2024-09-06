package dev.lazygarde.base.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import dev.lazygarde.base.BaseActivity
import dev.lazygarde.base.databinding.ActivitySplashBinding
import dev.lazygarde.base.presentation.MainActivity
import dev.lazygarde.base.utils.PreferenceUtil
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var preferences: PreferenceUtil

    override val bindingInflater: (LayoutInflater) -> ActivitySplashBinding
        get() = ActivitySplashBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)

        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
            finish()
        }, 2000)
    }
}