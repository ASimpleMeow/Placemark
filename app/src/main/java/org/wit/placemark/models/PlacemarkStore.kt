package org.wit.placemark.models

interface PlacemarkStore {
  suspend fun findAll(): MutableList<PlacemarkModel>
  suspend fun findById(id:Long) : PlacemarkModel?
  suspend fun create(placemark: PlacemarkModel)
  suspend fun update(placemark: PlacemarkModel)
  suspend fun delete(placemark: PlacemarkModel)
  fun clear()
}