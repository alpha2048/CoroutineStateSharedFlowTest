package com.alpha2048.coroutinestatesharedflowtest

import android.app.Application
import com.alpha2048.coroutinestatesharedflowtest.di.repositoryModule
import com.alpha2048.coroutinestatesharedflowtest.di.usecaseModule
import com.alpha2048.coroutinestatesharedflowtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(listOf(repositoryModule, usecaseModule, viewModelModule))
        }
    }
}