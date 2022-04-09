package com.jcardenas.presentation.views

import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentFormUserBinding
import com.jcardenas.presentation.vm.FormUserViewModel

class FormUserFragment: BaseFragment<FormUserViewModel, FragmentFormUserBinding>()  {
    override fun getLayout(): Int = R.layout.fragment_form_user

    override fun getViewModelClass(): Class<FormUserViewModel> = FormUserViewModel::class.java

    override fun setupUI() {
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setHomeButtonEnabled(true)
    }
}