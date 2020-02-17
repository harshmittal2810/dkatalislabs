package com.harsh.dkatalislabs.di.components

import com.harsh.dkatalislabs.CustomApplication
import com.harsh.dkatalislabs.di.modules.ActivityModule
import com.harsh.dkatalislabs.di.modules.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ActivityModule::class, AndroidSupportInjectionModule::class])
interface ApplicationComponent : AndroidInjector<CustomApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<CustomApplication>()
}