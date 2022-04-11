package com.jcardenas.presentation.views

import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.jcardenas.domain.common.getFileName
import com.jcardenas.domain.entities.Image
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentGalleryBinding
import com.jcardenas.presentation.helpers.GenericAdapter
import com.jcardenas.presentation.vm.GalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class GalleryFragment: BaseFragment<FragmentGalleryBinding>() {

    private val viewModel: GalleryViewModel by viewModels()
    private val galleryAdapter = GenericAdapter<Image>(R.layout.item_image)
    private val loadingDialog: ProgressDialog by lazy {
        ProgressDialog.show(requireContext(), "Loading", "Loading", true)
    }

    override fun getLayout(): Int = R.layout.fragment_gallery
    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun setupUI() {
        setTitle(R.string.menu_gallery)
        binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvGallery.adapter = galleryAdapter
        setHasOptionsMenu(true)
        addObservers()
        viewModel.listAll()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_image, menu)
    }

    private fun addObservers(){
        viewModel.loading.observe(this, Observer {
            if(it) loadingDialog.show()
            else loadingDialog.dismiss()
        })

        viewModel.data.observe(this, Observer {
            galleryAdapter.addItems(it)
        })

        viewModel.error.observe(this, Observer {
            showError(DialogInterface.OnClickListener { dialog , _ ->
                dialog.dismiss()
            })
        })
    }

    private val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
        result?.let {
            try {
                var bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, it)
                } else {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(requireContext().contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
                val fileName = it.getFileName(requireContext())
                val extension = fileName.substring(fileName.indexOf("."))
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                viewModel.uploadImage(data, extension)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addImage -> {
                pickImages.launch("image/*")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}