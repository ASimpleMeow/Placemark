package org.wit.placemark.models.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.wit.placemark.models.PlacemarkModel

@Database(entities = arrayOf(PlacemarkModel::class), version = 1)
abstract class Database: RoomDatabase() {

  abstract fun placemarkDao(): PlacemarkDao
}