package com.jcardenas.presentation.views

import android.content.DialogInterface
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentMapBinding
import com.jcardenas.presentation.vm.MapViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment: BaseFragment<FragmentMapBinding>(), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModels()
    private var gMap: GoogleMap? = null
    private val defaultZoom = 18f
    private val defaultPosition = LatLng(19.451054,-99.125519)
    private val listMarkers = mutableListOf<Marker>()

    override fun getLayout(): Int = R.layout.fragment_map
    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun setupUI() {
        setTitle(R.string.menu_map)
        initGoogleMaps()
    }

    fun initGoogleMaps(){
        var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance()
            childFragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit()
            mapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        gMap = p0
        goTo(defaultPosition)
    }

    private fun goTo(position: LatLng, zoom: Float = defaultZoom){
        val currentZoom = gMap?.cameraPosition?.zoom ?: defaultZoom
        val fixZoom = if(currentZoom > defaultZoom) currentZoom else defaultZoom
        gMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(position, fixZoom))
    }

    override fun onResume() {
        super.onResume()
        addObservers()
    }

    override fun onPause() {
        super.onPause()
        viewModel.data.removeObservers(this)
        viewModel.error.removeObservers(this)
    }

    private fun addObservers(){
        viewModel.data.observe(this, Observer { list ->
            listMarkers.map {
                it.remove()
            }
            listMarkers.clear()
            list?.let {
                it.map { location ->
                    gMap?.addMarker(
                        MarkerOptions()
                            .position(LatLng(location.latitude, location.longitude))
                            .title(location.date)
                    )?.let { marker ->
                        listMarkers.add(marker)
                    }
                }
            }
            if(listMarkers.isNotEmpty()) {
                goTo(listMarkers.last().position)
            }
        })

        viewModel.error.observe(this, Observer {
            if(it){
                showError(DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
            }
        })

        viewModel.getLocations()
    }

}