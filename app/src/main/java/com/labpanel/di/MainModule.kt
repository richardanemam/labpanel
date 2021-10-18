package com.labpanel.di

import com.google.firebase.database.FirebaseDatabase
import com.labpanel.feature.common.presentation.view.splashscreen.SplashViewModel
import com.labpanel.feature.student.data.StudentRepository
import com.labpanel.feature.student.presentation.view.allopenings.AllOpeningsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val studentModule: Module = module {

    single { FirebaseDatabase.getInstance().getReference("RegisteredOpenings") }
    single { StudentRepository(databaseReference = get()) }

    viewModel {
        AllOpeningsViewModel(repository = get())
    }
}

val appModule: Module = module {
    viewModel {
        SplashViewModel()
    }
}