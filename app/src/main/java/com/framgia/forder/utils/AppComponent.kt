package com.framgia.forder.utils

import android.content.Context
import com.framgia.forder.data.source.local.sharedprf.SharedPrefsApi
import com.framgia.forder.data.source.remote.TokenRepository
import com.framgia.forder.data.source.remote.api.NetworkModule
import com.framgia.forder.data.source.remote.api.service.FOrderApi
import com.framgia.forder.utils.dagger.AppScope
import com.framgia.forder.utils.rx.BaseSchedulerProvider
import dagger.Component

/**
 * Created by Tuanlvt on 24/11/2017.
 */
@AppScope
@Component(
        modules = arrayOf(ApplicationModule::class, NetworkModule::class))
interface AppComponent {

    //============== Region for Repository ================//

    fun fOrderApi(): FOrderApi

    fun sharedPrefsApi(): SharedPrefsApi

    //=============== Region for common ===============//

    fun applicationContext(): Context

    fun baseSchedulerProvider(): BaseSchedulerProvider

    fun tokenRepository(): TokenRepository
}
