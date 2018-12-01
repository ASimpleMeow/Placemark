package org.wit.placemark.models.room

import android.content.Context
import androidx.room.Room
import org.jetbrains.anko.coroutines.experimental.bg
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.models.PlacemarkStore

class PlacemarkStoreRoom(val context: Context) : PlacemarkStore {

  var dao: PlacemarkDao

  init {
    val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
        .fallbackToDestructiveMigration()
        .build()
    dao = database.placemarkDao()
  }

  override suspend fun findAll(): MutableList<PlacemarkModel> {
    val deferredPlacemarks = bg{
      dao.findAll()
    }
    val placemarks = deferredPlacemarks.await()
    return placemarks
  }

  override suspend fun findById(id: Long): PlacemarkModel? {
    val deferredPlacemarks = bg{
      dao.findById(id)
    }
    val placemarks = deferredPlacemarks.await()
    return placemarks
  }

  override suspend fun create(placemark: PlacemarkModel) {
    bg{
      dao.create(placemark)
    }
  }

  override suspend fun update(placemark: PlacemarkModel) {
    bg{
      dao.update(placemark)
    }
  }

  override suspend fun delete(placemark: PlacemarkModel) {
    bg{
      dao.deletePlacemark(placemark)
    }
  }

  fun clear() {
  }
}