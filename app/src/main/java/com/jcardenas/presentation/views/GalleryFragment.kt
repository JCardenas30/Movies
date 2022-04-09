package com.jcardenas.presentation.views

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentGalleryBinding
import com.jcardenas.presentation.vm.GalleryViewModel

class GalleryFragment: BaseFragment<GalleryViewModel, FragmentGalleryBinding>() {
    override fun getLayout(): Int = R.layout.fragment_gallery
    override fun getViewModelClass(): Class<GalleryViewModel> = GalleryViewModel::class.java

    override fun setupUI() {
        setTitle(R.string.menu_gallery)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_image, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addImage -> {
                Toast.makeText(requireContext(), "addImage", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}