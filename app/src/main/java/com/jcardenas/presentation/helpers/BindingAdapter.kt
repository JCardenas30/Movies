package com.jcardenas.presentation.helpers

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.jcardenas.domain.common.Constants
import com.jcardenas.movies.R

@BindingAdapter("android:src")
fun byUrl(imageView: ImageView, url: String?) {
    url?.let { u ->
        Glide.with(imageView.context)
            .load(u)
            .error(R.drawable.ic_baseline_broken_image_24)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(imageView)
    }
}

@BindingAdapter("android:rating")
fun ajust(ratingBar: RatingBar, rating: Double?){
    val r: Float = rating?.toFloat() ?: 0.0f
    ratingBar.rating = r / 2.0f
}

@BindingAdapter("android:src")
fun byStorageRef(imageView: ImageView, reference: StorageReference?) {
    reference?.downloadUrl?.addOnSuccessListener {
        Glide.with(imageView.context)
            .load(it)
            .error(R.drawable.ic_baseline_broken_image_24)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(imageView)
    }
}

@BindingAdapter("android:loadProfile")
fun loadProfile(imageView: ImageView, profile: String?) {
    profile?.let { idImage ->
        val ref = Firebase.storage.reference.child("${Constants.PROFILES_REF}/${idImage}.jpg")
        byStorageRef(imageView, ref)
    }
}