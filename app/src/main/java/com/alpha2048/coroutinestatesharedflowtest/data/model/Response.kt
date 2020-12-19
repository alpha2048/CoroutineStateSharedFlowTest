package com.alpha2048.coroutinestatesharedflowtest.data.model

import com.alpha2048.coroutinestatesharedflowtest.domain.entity.RepoEntity
import com.alpha2048.coroutinestatesharedflowtest.domain.entity.RepoItemEntity

data class RepoResponse (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<RepoItemResponse>
) {
    fun toEntity() = RepoEntity(
        totalCount = total_count,
        incompleteResults = incomplete_results,
        items = items.map { it.toEntity() }
    )
}

data class RepoItemResponse (
    val id: Int,
    val name: String,
    val html_url: String,
    val stargazers_count: Int
) {
    fun toEntity() = RepoItemEntity(
        id = id,
        name = name,
        htmlUrl = html_url,
        stargazersCount = stargazers_count
    )
}