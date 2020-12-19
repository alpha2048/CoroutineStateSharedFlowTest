package com.alpha2048.coroutinestatesharedflowtest.domain.entity

data class RepoEntity (
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<RepoItemEntity>
)

data class RepoItemEntity (
    val id: Int,
    val name: String,
    val htmlUrl: String,
    val stargazersCount: Int
)