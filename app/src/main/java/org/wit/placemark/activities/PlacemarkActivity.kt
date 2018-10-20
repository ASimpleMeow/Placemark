package org.wit.placemark.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_placemark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.placemark.R
import org.wit.placemark.helpers.readImage
import org.wit.placemark.helpers.readImageFromPath
import org.wit.placemark.helpers.showImagePicker
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.Location
import org.wit.placemark.models.PlacemarkModel


class PlacemarkActivity : AppCompatActivity(), AnkoLogger {

  val IMAGE_REQUEST = 1
  val LOCATION_REQUEST = 2

  var placemark = PlacemarkModel()
  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark)
    app = application as MainApp

    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)

    var edit = false
    if (intent.hasExtra("placemark_edit")){
      placemark = intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
      btnAdd.setText(R.string.button_savePlacemark)
      placemarkTitle.setText(placemark.title)
      placemarkDescription.setText(placemark.description)
      if (placemark.image.isNotEmpty()) chooseImage.setText(R.string.button_changeImage)
      placemarkImage.setImageBitmap(readImageFromPath(this, placemark.image))
      edit = true
    }

    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }

    placemarkLocation.setOnClickListener {
      val location = Location(52.245696, -7.139102, 15f)
      if (placemark.zoom != 0f) {
        location.lat =  placemark.lat
        location.lng = placemark.lng
        location.zoom = placemark.zoom
      }
      startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
    }

    btnAdd.setOnClickListener() {
      placemark.title = placemarkTitle.text.toString()
      placemark.description = placemarkDescription.text.toString()
      if (placemark.title.isNotEmpty()) {
        if (edit){
          app.placemarks.update(placemark.copy())
          toast(R.string.toast_placemarkSaved)
        } else {
          app.placemarks.create(placemark.copy())
          toast(R.string.toast_placemarkAdded)
        }
        info("add Button Pressed: $placemarkTitle")
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }
      else {
        toast(R.string.toast_enterTitle)
      }
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_placemark, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> {
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }
      R.id.item_delete -> {
        app.placemarks.delete(placemark.copy())
        finish()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      IMAGE_REQUEST -> {
        if (data != null) {
          placemark.image = data.getData().toString()
          placemarkImage.setImageBitmap(readImage(this, resultCode, data))
          chooseImage.setText(R.string.button_changeImage)
        }
      }
      LOCATION_REQUEST -> {
        if (data != null) {
          val location = data.extras.getParcelable<Location>("location")
          placemark.lat = location.lat
          placemark.lng = location.lng
          placemark.zoom = location.zoom
        }
      }
    }
  }
}
