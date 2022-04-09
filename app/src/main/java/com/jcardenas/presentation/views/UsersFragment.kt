package com.jcardenas.presentation.views

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentUserBinding
import com.jcardenas.presentation.vm.UsersViewModel

class UsersFragment: BaseFragment<UsersViewModel, FragmentUserBinding>() {
    override fun getLayout(): Int = R.layout.fragment_user
    override fun getViewModelClass(): Class<UsersViewModel> = UsersViewModel::class.java

    override fun setupUI() {
        setTitle(R.string.menu_users)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_user, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addUser -> {
                findNavController().navigate(R.id.action_usersFragment_to_formUserFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}