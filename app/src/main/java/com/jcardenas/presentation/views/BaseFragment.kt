package com.jcardenas.presentation.views

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding>: Fragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setupUI()
        bindViewToModel()
        return binding.root
    }
    abstract fun getLayout(): Int
    abstract fun bindViewToModel()
    abstract fun setupUI()
    fun setTitle(@StringRes res: Int) {
        requireActivity().setTitle(res)
    }

    fun showError(clickListener: DialogInterface.OnClickListener){
        activity?.let {
            (it as MainActivity).showError(clickListener)
        }
    }
}