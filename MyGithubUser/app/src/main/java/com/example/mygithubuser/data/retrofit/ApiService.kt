package com.example.mygithubuser.data.retrofit

import com.example.mygithubuser.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") username: String
    ): Call<GithubResponse>
}