package com.justgo.ui.navigation

import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.justgo.R
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import com.justgo.ui.Arrive.ArriveActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import org.jetbrains.anko.toast


class NavigationActivity : AppCompatActivity(), OnMapReadyCallback {
    val viewModel by lazy { ViewModelProviders.of(this)[NavigationViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val intent = intent
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lng = intent.getDoubleExtra("lng", 0.0)
        val desLat = intent.getDoubleExtra("desLat", 0.0)
        val desLng = intent.getDoubleExtra("desLng", 0.0)
        val transType = intent.getIntExtra("transType", 0)
        val placeId = intent.getStringExtra("placeId")
        viewModel.getNavigation(transType.toString(), lat, lng, desLat, desLng)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.main_startTravel_mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
        viewModel.changeTextLiveEvent.observe(this, Observer {
            Log.d("NavigationActivity", "성공")
            navigation_trans_location_tv.text = viewModel.direction[viewModel.index].instruction
            navigation_tag_location_tv.text = viewModel.direction[viewModel.index + 1].let { "$lat, $lng" }
        })
//        viewModel.locationIndex.observe(this , Observer {
//            navigation_trans_location_tv.text = viewModel.direction[it!!].instruction
//        })
        viewModel.travelFinishEvent.observe(this, Observer {
            toast("Travel Is Finish!")
            val arriveIntent = Intent(this, ArriveActivity::class.java)
            arriveIntent.putExtra("placeId", placeId)
            startActivity(arriveIntent)
            finish()
        })
    }

    override fun onMapReady(map: GoogleMap) {
        map.uiSettings.isScrollGesturesEnabled = false
        map.uiSettings.isZoomGesturesEnabled = false
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        viewModel.polyLineEvent.observe(this, Observer {
            val d = PolyUtil.decode(viewModel.polyLine)
            map.addPolyline(PolylineOptions().addAll(d)
                    .width(5F))
            viewModel.direction.forEach {
                LatLng(it.lat, it.lng).let {
                    map.addMarker(MarkerOptions().position(it))
                }
            }
        })

        if (ContextCompat.checkSelfPermission(baseContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED) {
            map.isMyLocationEnabled = true
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10F, object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 17F).let {
                        map.animateCamera(it)
                        viewModel.compareLocation(location.latitude, location.longitude)
                    }
                }

                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                }

                override fun onProviderEnabled(p0: String?) {
                }

                override fun onProviderDisabled(p0: String?) {
                }

            })
        }
    }
}
