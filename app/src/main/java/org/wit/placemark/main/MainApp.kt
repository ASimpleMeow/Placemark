package org.wit.placemark.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.placemark.models.json.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.room.PlacemarkStoreRoom

class MainApp : Application(), AnkoLogger {

  lateinit var placemarks: PlacemarkStore

  override fun onCreate() {
    super.onCreate()
    placemarks = PlacemarkStoreRoom (applicationContext)
    info("Placemark started")
  }
}