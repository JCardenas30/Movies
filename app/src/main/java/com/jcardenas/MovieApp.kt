package com.jcardenas

import android.app.Application
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp: Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(applicationContext)
    }
}