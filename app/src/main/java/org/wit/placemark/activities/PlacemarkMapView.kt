package org.wit.placemark.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.placemark.R

import kotlinx.android.synthetic.main.activity_placemark_maps.*
import kotlinx.android.synthetic.main.content_placemark_maps.*
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.main.MainApp

class PlacemarkMapView : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

  lateinit var map: GoogleMap
  lateinit var presenter: PlacemarkMapPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark_maps)
    setSupportActionBar(toolbarMaps)

    mapView.onCreate(savedInstanceState)
    mapView.getMapAsync{
      map = it
      map.setOnMarkerClickListener(this)
      presenter.configureMap(map)
    }
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    val tag = marker.tag as Long
    val placemark = presenter.app.placemarks.findById(tag)
    currentTitle.text = placemark!!.title
    currentDescription.text = placemark!!.description
    imageView.setImageBitmap(readImageFromPath(this@PlacemarkMapView, placemark.image))
    return true
  }

  override fun onDestroy() {
    super.onDestroy()
    mapView.onDestroy()
  }

  override fun onLowMemory() {
    super.onLowMemory()
    mapView.onLowMemory()
  }

  override fun onPause() {
    super.onPause()
    mapView.onPause()
  }

  override fun onResume() {
    super.onResume()
    mapView.onResume()
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    super.onSaveInstanceState(outState)
    mapView.onSaveInstanceState(outState)
  }
}
