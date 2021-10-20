package com.di

import com.data.api.FirebaseService
import com.data.mappers.OpeningsMapper
import com.data.repository.ProfessorRepositoryImpl
import com.domain.usecase.ProfileUseCase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.presentation.view.openingregistration.OpeningRegistrationViewModel
import com.presentation.view.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val professorModule: Module = module {

    factory { Firebase.auth }
    factory { FirebaseDatabase.getInstance() }
    factory { FirebaseDatabase.getInstance().getReference("RegisteredOpenings") }
    factory { FirebaseService(auth = get(), databaseReference = get()) }
    factory {
        ProfessorRepositoryImpl(
            firebaseService = get(),
            openingsMapper = OpeningsMapper()
        )
    }
    factory {
        ProfileUseCase(
            repository = get()
        )
    }

    viewModel {
        ProfileViewModel(
            useCase = get()
        )
    }

    viewModel {
        OpeningRegistrationViewModel(repository = get())
    }
}