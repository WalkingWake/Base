package dev.lazygarde.base.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import dev.lazygarde.base.databinding.ActivityMainBinding
import dev.lazygarde.base.BaseActivity
import dev.lazygarde.base.utils.PreferenceUtil
import dev.lazygarde.multi.theme.Const.THEME
import dev.lazygarde.multi.theme.MultiThemeHelper
import dev.lazygarde.multi.theme.SelectThemeActivity
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var preferences: PreferenceUtil
    private val getCustomThemeHandler: GetCustomThemeHandler by lazy {
        GetCustomThemeHandler(activityResultRegistry)
    }

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        super.onViewBindingCreated(savedInstanceState)
        lifecycle.addObserver(getCustomThemeHandler)

        preferences.isFirstRun = false

        binding.tvMain.setOnClickListener {
            getCustomThemeHandler.getCustomTheme(preferences.theme) {
                preferences.theme = it ?: preferences.theme
//                MultiThemeHelper.setUpActivityWithSavedTheme(this)
            }
        }
    }
}

class GetCustomThemeHandler(
    private val registry: ActivityResultRegistry
) : DefaultLifecycleObserver {


    private var launcher: ActivityResultLauncher<Int>? = null
    private var onResult: ((Int?) -> Unit)? = null

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        launcher = registry.register(REGISTRY_KEY, owner, GetCustomThemeContract()) {
            onResult?.invoke(it)
        }
    }

    fun getCustomTheme(input: Int, onResult: (Int?) -> Unit) {
        this.onResult = onResult
        launcher?.launch(input)
    }

    companion object {
        private const val REGISTRY_KEY = "GetCustomThemeHandler"
    }

}

class GetCustomThemeContract : ActivityResultContract<Int, Int?>() {
    override fun createIntent(context: Context, input: Int): Intent {
        return Intent(context, SelectThemeActivity::class.java).apply {
            putExtra(THEME, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Int? {
        if (resultCode == AppCompatActivity.RESULT_OK) {
            return intent?.getIntExtra(THEME, -1)
        }
        return null
    }

}