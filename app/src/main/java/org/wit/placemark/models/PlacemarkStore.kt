package org.wit.placemark.models

interface PlacemarkStore {
  fun finalAll(): MutableList<PlacemarkModel>
  fun create(placemark: PlacemarkModel)
  fun update(placemark: PlacemarkModel)
  fun delete(placemark: PlacemarkModel)
}