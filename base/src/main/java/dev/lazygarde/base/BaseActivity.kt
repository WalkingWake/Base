package dev.lazygarde.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dev.lazygarde.multi.theme.MultiThemeHelper

abstract class BaseActivity<BINDING : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: BINDING
        private set

    abstract val bindingInflater: (LayoutInflater) -> BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        initWindow()
        MultiThemeHelper.setUpActivityWithSavedTheme(this)
        super.onCreate(savedInstanceState)

        binding = bindingInflater.invoke(layoutInflater).apply {
            setContentView(root)
        }
        onViewBindingCreated(savedInstanceState)
    }

    private fun initWindow() {
        window.statusBarColor = Color.TRANSPARENT

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    open fun onViewBindingCreated(savedInstanceState: Bundle?) {}
}