package org.wit.placemark.activities

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.content_placemark_maps.*
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel

class PlacemarkMapPresenter(val view: PlacemarkMapView) {

  lateinit var app: MainApp

  init {
    app = view.application as MainApp
  }

  fun configureMap(map: GoogleMap){
    map.uiSettings.setZoomControlsEnabled(true)
    app.placemarks.finalAll().forEach {
      val loc = LatLng(it.lat, it.lng)
      val options = MarkerOptions().title(it.title).position(loc)
      map.addMarker(options).tag = it.id
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
    }
  }
}