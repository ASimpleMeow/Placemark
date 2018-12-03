package org.wit.placemark.views.placemarklist

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.placemark.views.map.PlacemarkMapView
import org.wit.placemark.views.placemark.PlacemarkView
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.views.BasePresenter
import org.wit.placemark.views.VIEW

class PlacemarkListPresenter(view: PlacemarkListView): BasePresenter(view) {

  fun doAddPlacemark() {
    view?.navigateTo(VIEW.PLACEMARK)
  }

  fun doEditPlacemark(placemark: PlacemarkModel) {
    view?.navigateTo(VIEW.PLACEMARK, 0, "placemark_edit", placemark)
  }

  fun doShowPlacemarksMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    app.placemarks.clear()
    view?.navigateTo(VIEW.LOGIN)
  }

  fun loadPlacemarks(){
    async(UI) {
      view?.showPlacemarks(app.placemarks.findAll())
    }
  }
}