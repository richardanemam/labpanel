package com.di

import com.data.api.StudentFirebaseService
import com.data.mappers.OpeningsMapper
import com.data.repository.StudentRepositoryImpl
import com.domain.repository.StudentRepository
import com.domain.usecase.AllOpeningsUseCase
import com.google.firebase.database.FirebaseDatabase
import com.presentation.view.AllOpeningsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val studentModule: Module = module {

    factory { FirebaseDatabase.getInstance().getReference("RegisteredOpenings") }
    factory { StudentFirebaseService(databaseReference = get()) }
    factory<StudentRepository> { StudentRepositoryImpl(studentFirebaseService = get(), openingsMapper = OpeningsMapper()) }
    factory { AllOpeningsUseCase(repository = get()) }

    viewModel {
        AllOpeningsViewModel(useCase = get())
    }
}