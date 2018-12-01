package org.wit.placemark.models.mem

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.models.PlacemarkStore

var lastId = 0L

internal fun getId():Long{
  return lastId++
}

class PlacemarkMemStore: PlacemarkStore, AnkoLogger{

  val placemarks = ArrayList<PlacemarkModel>()

  override suspend fun findAll():MutableList<PlacemarkModel>{
    return placemarks
  }

  override suspend fun findById(id:Long) : PlacemarkModel? {
    return placemarks.find { it.id == id }
  }

  override suspend fun create(placemark: PlacemarkModel){
    placemark.id = getId()
    placemarks.add(placemark)
    logAll();
  }

  override suspend fun update(placemark: PlacemarkModel) {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == placemark.id}
    if (foundPlacemark != null){
      foundPlacemark.title = placemark.title
      foundPlacemark.description = placemark.description
      foundPlacemark.image = placemark.image
      foundPlacemark.location = placemark.location
      logAll()
    }
  }

  override suspend fun delete(placemark: PlacemarkModel) {
    placemarks.remove(placemark)
  }

  fun logAll(){
    placemarks.forEach { info("${it}") }
  }
}