package com.harsh.dkatalislabs.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Author: Harsh Mittal
 * Date:   2020-02-14
 * Github: https://github.com/harshmittal2810
 */

class AppSchedulerProvider : SchedulerProvider {
    override fun ioScheduler() = Schedulers.io()

    override fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}