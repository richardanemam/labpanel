package com.labpanel.di

import com.labpanel.feature.splashscreen.presentation.view.splashscreen.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    viewModel {
        SplashViewModel()
    }
}