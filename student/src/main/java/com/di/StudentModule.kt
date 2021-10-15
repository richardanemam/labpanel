package com.di

import com.data.StudentRepository
import com.google.firebase.database.FirebaseDatabase
import com.presentation.view.AllOpeningsViewModel
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