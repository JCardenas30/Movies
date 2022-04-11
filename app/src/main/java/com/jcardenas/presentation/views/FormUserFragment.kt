package com.jcardenas.presentation.views

import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jcardenas.domain.common.getFileName
import com.jcardenas.domain.entities.User
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentFormUserBinding
import com.jcardenas.presentation.helpers.UserFields
import com.jcardenas.presentation.vm.FormUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class FormUserFragment: BaseFragment<FragmentFormUserBinding>()  {

    private val viewModel: FormUserViewModel by viewModels()
    private var hasProfileImage = false
    private val loadingDialog: ProgressDialog by lazy {
        ProgressDialog.show(requireContext(), "Loading", "Loading", true)
    }

    override fun getLayout(): Int = R.layout.fragment_form_user
    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }
    override fun setupUI() {
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setHomeButtonEnabled(true)
        addObservers()
        setListeners()
    }

    private fun addObservers() {
        viewModel.loading.observe(this, Observer {
            if(it) loadingDialog.show()
            else loadingDialog.dismiss()
        })

        viewModel.success.observe(this, Observer { success ->
            loadingDialog.dismiss()
            success?.let {
                if(it) {
                    findNavController().popBackStack()
                }
                else {
                    showError(DialogInterface.OnClickListener { dialog, _ -> dialog.dismiss()  })
                }
            }
        })

        viewModel.inputsErros.observe(this, Observer {
            binding.tilAddress.error = null
            binding.tilEmail.error = null
            binding.tilFullName.error = null
            binding.tilKey.error = null
            binding.tilPhoneNumber.error = null

            it.forEach { field ->
                when(field) {
                    UserFields.employeeNumber -> binding.tilKey.error = getString(R.string.error_required)
                    UserFields.fullName -> binding.tilFullName.error = getString(R.string.error_required)
                    UserFields.phone -> binding.tilPhoneNumber.error = getString(R.string.error_required)
                    UserFields.email -> binding.tilEmail.error = getString(R.string.error_required)
                    UserFields.address -> binding.tilAddress.error = getString(R.string.error_required)
                }
            }
        })
    }

    private fun setListeners(){
        binding.btnSave.setOnClickListener {
            val user = getUser()
            if(viewModel.isValid(user)) {
                var imageData: ByteArray? = null
                if(hasProfileImage) {
                    binding.imgProfile.isDrawingCacheEnabled = true
                    binding.imgProfile.buildDrawingCache()

                    val bitmap = (binding.imgProfile.drawable as BitmapDrawable).bitmap
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                    imageData = baos.toByteArray()
                }
                viewModel.insertUsert(imageData, user)
            }
        }

        binding.btnUploadProfile.setOnClickListener {
            pickImages.launch("image/*")
        }
    }

    private val pickImages = registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
        result?.let {
            Glide.with(requireContext())
                .load(it)
                .placeholder(R.drawable.ic_baseline_person_24)
                .error(R.drawable.ic_baseline_person_24)
                .into(binding.imgProfile)
            hasProfileImage = true
        }
    }

    private fun getUser(): User {
        return User(
            employeeNumber = binding.etKey.text.toString().trim(),
            fullName = binding.etFullName.text.toString().trim(),
            address = binding.etAddress.text.toString().trim(),
            phone = binding.etPhone.text.toString().trim(),
            email = binding.etEmail.text.toString().trim()
        )
    }

}