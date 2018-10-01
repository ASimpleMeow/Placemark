package org.wit.placemark.models

interface PlacemarkStore {
  fun finalAll(): List<PlacemarkModel>
  fun create(placemark: PlacemarkModel)
  fun update(placemark: PlacemarkModel)
}