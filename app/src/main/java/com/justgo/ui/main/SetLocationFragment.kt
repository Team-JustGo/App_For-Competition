package com.justgo.ui.main


import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.justgo.R
import org.jetbrains.anko.find
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import android.location.LocationManager
import android.widget.Button
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.justgo.Util.DataBindingFragment
import com.justgo.databinding.FragmentSetLocationBinding
import kotlinx.android.synthetic.main.fragment_set_location.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.find


class SetLocationFragment : DataBindingFragment<FragmentSetLocationBinding>(), OnMapReadyCallback, LocationListener {
    override fun getLayoutId(): Int = R.layout.fragment_set_location

    lateinit var googleMap: GoogleMap
    val viewModel by lazy { ViewModelProviders.of(activity!!)[MainViewModel::class.java] }
    val locationManager: LocationManager by lazy { activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    val mapView: MapView  by lazy { view.find<MapView>(R.id.setLocation_map) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding.mainViewModel = viewModel
        permissionCheck()
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        if (ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 400, 1000F, this);
        view.find<Button>(R.id.setLocation_submit_btn).onClick {
            Log.d("Camera Position", googleMap.cameraPosition.target.toString())
        }
        return view
    }

    fun permissionCheck() {
        val permissionlistener = object : PermissionListener {
            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            }

            override fun onPermissionGranted() {
            }
        }

        TedPermission.with(context)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("서비스를 이용하기 위해서 GPS 권한이 필요합니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map
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
        }
    }

    override fun onLocationChanged(location: Location) {
        CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 10F).let {
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
