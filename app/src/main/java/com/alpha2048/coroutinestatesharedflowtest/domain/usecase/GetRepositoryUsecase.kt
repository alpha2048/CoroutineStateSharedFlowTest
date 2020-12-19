package com.alpha2048.coroutinestatesharedflowtest.domain.usecase

import com.alpha2048.coroutinestatesharedflowtest.data.repository.GithubRepository
import com.alpha2048.coroutinestatesharedflowtest.domain.entity.RepoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetRepositoryUsecase(
    private val repository: GithubRepository
) {
    suspend fun execute(q: String, page: Int): Flow<RepoEntity> = flow {
        emit(repository.getRepositoryList(q, page).toEntity())
    }.flowOn(Dispatchers.IO)
}