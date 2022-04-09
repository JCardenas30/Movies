package com.jcardenas.presentation.views

import com.google.android.gms.maps.*
import com.jcardenas.movies.R
import com.jcardenas.movies.databinding.FragmentMapBinding
import com.jcardenas.presentation.vm.MapViewModel

class MapFragment: BaseFragment<MapViewModel, FragmentMapBinding>(), OnMapReadyCallback {
    override fun getLayout(): Int = R.layout.fragment_map
    override fun getViewModelClass(): Class<MapViewModel> = MapViewModel::class.java

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

    }

}