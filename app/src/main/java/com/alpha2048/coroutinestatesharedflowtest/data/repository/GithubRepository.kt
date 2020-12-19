package com.alpha2048.coroutinestatesharedflowtest.data.repository

import com.alpha2048.coroutinestatesharedflowtest.data.model.RepoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class GithubRepository {
    private var retrofit: Retrofit

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(
                OkHttpClient
                    .Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }

    suspend fun getRepositoryList(q: String, page: Int): RepoResponse {
        val service = retrofit.create(GithubApiInterface::class.java)
        return service.getGithubRepository(q, page)
    }
}

interface GithubApiInterface {
    @GET("/search/repositories")
    suspend fun getGithubRepository(@Query("q") q: String,
                                    @Query("page") page: Int): RepoResponse
}