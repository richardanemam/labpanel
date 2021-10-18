package com.labpanel.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.labpanel.feature.common.presentation.view.splashscreen.SplashViewModel
import com.labpanel.feature.professor.data.professorrepository.ProfessorRepository
import com.labpanel.feature.professor.presentation.view.openingregistration.OpeningRegistrationViewModel
import com.labpanel.feature.professor.presentation.view.profile.ProfileViewModel
import com.labpanel.feature.student.data.StudentRepository
import com.labpanel.feature.student.presentation.view.allopenings.AllOpeningsViewModel
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