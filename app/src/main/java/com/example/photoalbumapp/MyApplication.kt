package com.example.photoalbumapp

import android.app.Application
import com.example.photoalbumapp.di.AppComponent
import com.example.photoalbumapp.di.DaggerAppComponent

class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}