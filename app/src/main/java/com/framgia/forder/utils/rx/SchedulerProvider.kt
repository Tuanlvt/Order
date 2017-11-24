package com.framgia.forder.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Tuanlvt on 24/11/2017.
 */
class SchedulerProvider private constructor() : BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    private object SchedulerProvider {
        val instance = SchedulerProvider()
    }

    companion object {
        val instance: com.framgia.forder.utils.rx.SchedulerProvider by lazy {
            SchedulerProvider
                    .instance
        }
    }
}
