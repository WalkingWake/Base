package dev.lazygarde.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING : ViewBinding> : Fragment() {
    protected lateinit var binding: BINDING
        private set

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> BINDING

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = bindingInflater.invoke(inflater, container, false)
        return binding.root
    }
}