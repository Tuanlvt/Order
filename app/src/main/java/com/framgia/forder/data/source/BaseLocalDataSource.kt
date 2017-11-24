package com.framgia.forder.data.source

/**
 * Created by Tuanlvt on 24/11/2017.
 */
interface BaseLocalDataSource {
    fun openTransaction()

    fun closeTransaction()
}
