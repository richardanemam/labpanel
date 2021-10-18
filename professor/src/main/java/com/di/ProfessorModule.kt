package com.di

import com.data.ProfessorRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.presentation.view.openingregistration.OpeningRegistrationViewModel
import com.presentation.view.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val professorModule: Module = module {

    single { Firebase.auth }
    single { FirebaseDatabase.getInstance() }
    single { FirebaseDatabase.getInstance().getReference("RegisteredOpenings") }
    single { ProfessorRepository(auth = get(), databaseReference = get()) }

    viewModel {
        ProfileViewModel(
            repository = get()
        )
    }

    viewModel {
        OpeningRegistrationViewModel(repository = get())
    }
}