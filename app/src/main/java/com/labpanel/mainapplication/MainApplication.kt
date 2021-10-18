package com.labpanel.mainapplication

import android.app.Application
import com.di.professorModule
import com.di.studentModule
import com.labpanel.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            modules(
                listOf(
                    professorModule,
                    studentModule,
                    appModule
                )
            )
        }
    }
}