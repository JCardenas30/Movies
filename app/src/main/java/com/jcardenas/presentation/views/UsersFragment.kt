package com.jcardenas.presentation.views

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.jcardenas.domain.entities.User
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentUserBinding
import com.jcardenas.presentation.helpers.GenericAdapter
import com.jcardenas.presentation.vm.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment: BaseFragment<FragmentUserBinding>() {

    private val viewModel: UsersViewModel by viewModels()
    private val userAdapter = GenericAdapter<User>(R.layout.item_user)

    override fun getLayout(): Int = R.layout.fragment_user
    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun setupUI() {
        setTitle(R.string.menu_users)
        binding.rvUsers.adapter = userAdapter
        setHasOptionsMenu(true)
        addObservers()
    }

    fun addObservers(){
        viewModel.all().asLiveData().observe(this, Observer {
            userAdapter.addItems(it)
        })
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