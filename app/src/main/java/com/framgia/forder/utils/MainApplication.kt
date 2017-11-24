package com.framgia.forder.utils

import android.support.multidex.MultiDexApplication
import com.framgia.forder.data.source.remote.api.NetworkModule

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class MainApplication : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .applicationModule(ApplicationModule(applicationContext))
                .networkModule(NetworkModule(this))
                .build()
    }
}