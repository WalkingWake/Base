package dev.lazygarde.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import androidx.viewbinding.ViewBinding


abstract class BaseDialog<BINDING : ViewBinding>(
    context: Context,
    themeResId: Int = R.style.ThemeDialog
) : Dialog(context, themeResId) {
    protected lateinit var binding: BINDING
        private set

    abstract val bindingInflater: (LayoutInflater) -> BINDING


    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        createContentView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onViewBindingCreated(savedInstanceState)
    }

    private fun createContentView() {
        binding = bindingInflater.invoke(layoutInflater).apply {
            setContentView(root)
        }
    }

    open fun onViewBindingCreated(savedInstanceState: Bundle?) {}


    fun setDialogBottom() {
        window?.run {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setGravity(Gravity.BOTTOM)
        }
    }
}