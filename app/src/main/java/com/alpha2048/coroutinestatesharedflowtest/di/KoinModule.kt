package com.alpha2048.coroutinestatesharedflowtest.di

import com.alpha2048.coroutinestatesharedflowtest.data.repository.GithubRepository
import com.alpha2048.coroutinestatesharedflowtest.domain.usecase.GetRepositoryUsecase
import com.alpha2048.coroutinestatesharedflowtest.presentation.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule : Module = module {
    single { GithubRepository() }
}

val usecaseModule: Module = module {
    single { GetRepositoryUsecase(get()) }
}

val viewModelModule : Module = module {
    viewModel { MainViewModel(get()) }
}