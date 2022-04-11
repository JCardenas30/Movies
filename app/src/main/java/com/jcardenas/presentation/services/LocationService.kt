package com.jcardenas.presentation.services

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.jcardenas.domain.common.DateUtil
import com.jcardenas.domain.usecases.SaveLocationUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LocationService: Service(), LocationListener {

    @Inject
    lateinit var saveLocationUseCase: SaveLocationUseCase
    private lateinit var locationManager: LocationManager
    private var location: Location? = null
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0f, this
            )
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object: Runnable {
            override fun run() {
                location?.let {
                    val item = com.jcardenas.domain.entities.Location(
                        latitude = it.latitude,
                        longitude = it.longitude,
                        date = DateUtil.getDateByFormat(DateUtil.DATE_TIME_FORMAT)
                    )

                    scope.launch {
                        saveLocationUseCase.saveLocation(item).collect {
                            Log.d("resultSave", "${it.toString()}")
                        }
                    }
                    Log.e("Location", "lat: ${it.latitude} lon: ${it.longitude}")
                }
                mainHandler.postDelayed(this, 15*60*1000)
            }

        })
        return START_NOT_STICKY
    }

    override fun onLocationChanged(p0: Location) {
        location = p0
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        locationManager.removeUpdates(this)
    }
}