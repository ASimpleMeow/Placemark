package org.wit.placemark.views

import android.content.Intent
import org.wit.placemark.main.MainApp

open class BasePresenter(var view: BaseView?) {

  var app: MainApp = view?.application as MainApp

  open fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent){

  }

  open fun doRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResult: IntArray){

  }

  open fun onDestroy(){
    view = null
  }
}