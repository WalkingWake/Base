package dev.lazygarde.multi.theme

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dev.lazygarde.multi.theme.Const.SELECTED_THEME
import dev.lazygarde.multi.theme.Const.THEME
import dev.lazygarde.multi.theme.databinding.ActivitySelectThemeBinding
import kotlin.math.abs

class SelectThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectThemeBinding

    private var currentViewPagerIndex = 0
    private var viewPager2: ViewPager2? = null
    private val customThemes = MultiThemeHelper.getCustomThemes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val theme = intent.getIntExtra(THEME, -1)
        initSavedTheme(theme)

        initWindow()
        initListener()
        setUpViewPager()
    }

    private fun initSavedTheme(theme: Int) {
        val savedTheme = customThemes.find { it.theme == theme } ?: customThemes[0]
        setCustomTheme(savedTheme)
        viewPager2?.setCurrentItem(customThemes.indexOf(savedTheme) + 1, false)
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

    private fun setCustomTheme(customTheme: CustomTheme) {
        binding.ivBackground.setImageResource(customTheme.backgroundImage)
        setUpUseNowButton(customTheme.primaryColor)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            !customTheme.isDark
        if (customTheme.isDark) {
            binding.tvTitle.setTextColor(Color.WHITE)
            binding.vTitleBackground.setBackgroundColor(Color.parseColor("#0085ff"))
        } else {
            binding.tvTitle.setTextColor(Color.BLACK)
            binding.vTitleBackground.setBackgroundColor(Color.parseColor("#72FFEF"))
        }
    }

    private fun initListener() {
        binding.tvLater.setOnClickListener {
            finish()
        }
        binding.tvUseNow.setOnClickListener {
            val intent = Intent()
            intent.putExtra(SELECTED_THEME, customThemes[currentViewPagerIndex].theme)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun setUpViewPager() {
        viewPager2 = binding.vpThemes
        val viewPagerSize = customThemes.size + 2

        viewPager2?.apply {

            adapter = ThemeAdapter(customThemes.map { it.previewImage })
            currentItem = 1

            offscreenPageLimit = 3
            clipToPadding = false
            clipChildren = false

            setPageTransformer(
                CompositePageTransformer().apply {
                    addTransformer(MarginPageTransformer(40))
                    addTransformer { page, position ->
                        val r = 1 - abs(position)
                        page.scaleY = 0.85f + r * 0.14f
                    }
                }
            )

            registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                        if (state == ViewPager2.SCROLL_STATE_IDLE) {
                            when (viewPager2?.currentItem) {
                                viewPagerSize - 1 -> viewPager2?.setCurrentItem(1, false)
                                0 -> viewPager2?.setCurrentItem(viewPagerSize - 2, false)
                            }
                        }
                    }

                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        val currentViewPagerIndex =
                            (position - 1 + customThemes.size) % customThemes.size
                        this@SelectThemeActivity.currentViewPagerIndex = currentViewPagerIndex
                        setCustomTheme(customThemes[currentViewPagerIndex])
                    }
                }
            )
        }
    }

    private fun setUpUseNowButton(color: Int) {
        val shapeDrawable = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    12f.dpToPx(),
                    12f.dpToPx(),
                    12f.dpToPx(),
                    12f.dpToPx(),
                    12f.dpToPx(),
                    12f.dpToPx(),
                    12f.dpToPx(),
                    12f.dpToPx()
                ),
                null,
                null
            )
        ).apply {
            paint.color = color
            paint.isAntiAlias = true
            paint.style = android.graphics.Paint.Style.FILL
        }
        binding.tvUseNow.background = shapeDrawable
    }

    private fun Float.dpToPx(): Float = this * resources.displayMetrics.density
}