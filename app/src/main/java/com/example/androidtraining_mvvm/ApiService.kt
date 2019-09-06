package com.example.androidtraining_mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/repos/{owner}/{repo}/contributors")
    fun getContributor(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<Contributor>>
}