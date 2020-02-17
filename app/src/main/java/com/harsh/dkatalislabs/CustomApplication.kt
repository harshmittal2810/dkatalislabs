package com.harsh.dkatalislabs

import com.harsh.dkatalislabs.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Author: Harsh Mittal
 * Date:   2020-02-14
 * Github: https://github.com/harshmittal2810
 */
class CustomApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

}