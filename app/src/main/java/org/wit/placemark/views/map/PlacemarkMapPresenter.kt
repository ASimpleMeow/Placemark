package org.wit.placemark.views.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.placemark.main.MainApp
import org.wit.placemark.views.BasePresenter
import org.wit.placemark.views.BaseView

class PlacemarkMapPresenter(view: BaseView): BasePresenter(view) {

  fun doPopulateMap(map: GoogleMap){
    map.uiSettings.setZoomControlsEnabled(true)
    map.setOnMarkerClickListener(view as PlacemarkMapView)
    app.placemarks.findAll().forEach {
      val loc = LatLng(it.lat, it.lng)
      val options = MarkerOptions().title(it.title).position(loc)
      map.addMarker(options).tag = it.id
      map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
    }
  }

  fun doMarkerSelected(marker: Marker){
    val tag = marker.tag as Long
    val placemark = app.placemarks.findById(tag)
    if (placemark != null) view?.showPlacemark(placemark)
  }
}