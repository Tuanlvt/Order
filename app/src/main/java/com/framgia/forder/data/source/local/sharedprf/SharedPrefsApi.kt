package com.framgia.forder.data.source.local.sharedprf

/**
 * Created by Tuanlvt on 24/11/2017.
 */
interface SharedPrefsApi {
    operator fun <T> get(key: String, clazz: Class<T>): T?

    fun <T> put(key: String, data: T)

    fun clear()
}
