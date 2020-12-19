package com.alpha2048.coroutinestatesharedflowtest.presentation.viewmodel

import com.alpha2048.coroutinestatesharedflowtest.domain.entity.RepoItemEntity
import androidx.lifecycle.*
import com.alpha2048.coroutinestatesharedflowtest.domain.usecase.GetRepositoryUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val usecase: GetRepositoryUsecase
) : ViewModel() {

    private val _repoItems: MutableStateFlow<List<RepoItemEntity>> = MutableStateFlow(emptyList())
    val repoItems: StateFlow<List<RepoItemEntity>> = _repoItems

    fun loadData() {
        viewModelScope.launch {
            usecase.execute("Coroutine", 1).collect {
                val list = _repoItems.value
                _repoItems.value = list + it.items
            }
        }
    }
}