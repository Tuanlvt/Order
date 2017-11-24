package com.framgia.forder.utils.rx

import io.reactivex.Scheduler

/**
 * Created by Tuanlvt on 24/11/2017.
 */
interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
