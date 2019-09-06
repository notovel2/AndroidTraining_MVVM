package com.example.androidtraining_mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContributorViewmodel(
    private var contributorLiveData: MutableLiveData<List<Contributor>>,
    private var githubService: ApiService

): ViewModel() {
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        githubService = retrofit.create(githubService.javaClass)
    }

    fun serchContributors(keyword: String) {
        githubService
            .getContributor(
                "facebook",
                "react"
            )
            .enqueue(object: Callback<List<Contributor>> {
                override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<Contributor>>,
                    response: Response<List<Contributor>>
                ) {
                    contributorLiveData.value = response.body()
                }

            })
    }

    fun getContributors(): LiveData<List<Contributor>> {
        if (contributorLiveData == null) {
            contributorLiveData = MutableLiveData()
        }
        return contributorLiveData
    }
}