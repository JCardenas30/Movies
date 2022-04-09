package com.jcardenas.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.library.baseAdapters.BR

abstract class BaseFragment<V: ViewModel, T: ViewDataBinding>: Fragment() {

    lateinit var binding: T
    lateinit var viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        viewModel = ViewModelProvider(this).get(getViewModelClass())
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setupUI()
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }
    abstract fun getLayout(): Int
    abstract fun getViewModelClass(): Class<V>
    abstract fun setupUI()
    fun setTitle(@StringRes res: Int) {
        requireActivity().setTitle(res)
    }
}