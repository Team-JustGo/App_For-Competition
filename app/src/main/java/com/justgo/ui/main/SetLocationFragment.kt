package com.justgo.ui.main


import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.justgo.R
import com.justgo.Util.DataBindingFragment
import com.justgo.databinding.FragmentSetLocationBinding
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.find
import org.jetbrains.anko.info


class SetLocationFragment : DataBindingFragment<FragmentSetLocationBinding>(), OnMapReadyCallback, LocationListener, AnkoLogger {
    override fun getLayoutId(): Int = R.layout.fragment_set_location

    lateinit var googleMap: GoogleMap
    val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }
    val locationManager: LocationManager by lazy { activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    val mapView: MapView  by lazy { view.find<MapView>(R.id.setLocation_map) }
    var isMapCalled = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
//        if (!isMapCalled) {
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        isMapCalled = true
//        }
        binding.mainViewModel = viewModel
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1000F, this);
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map
        mapView.onStart()
        MapsInitializer.initialize(activity)
//        googleMap.setOnCameraMoveListener {
//            marker.setPosition(googleMap.cameraPosition.target)//to center in map
//        }
        Log.d("GoogleMap", "Map is called")
        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED)
            googleMap.isMyLocationEnabled = true

        googleMap.setOnCameraIdleListener {
            val cameraPosition = map.cameraPosition
            val currentCenter = cameraPosition.target
            viewModel.setLat(googleMap.cameraPosition.target.latitude)
            viewModel.setLng(googleMap.cameraPosition.target.longitude)
            info("${viewModel.lat.value}, ${viewModel.lng.value}")
        }
    }

    override fun onLocationChanged(location: Location) {
        CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15F).let {
            googleMap.animateCamera(it)
            locationManager.removeUpdates(this);
        }
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
//        fragmentManager!!.beginTransaction().detach(this).attach(this).commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

}
