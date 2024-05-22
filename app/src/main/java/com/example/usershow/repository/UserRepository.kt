package com.example.usershow.repository

import com.example.usershow.ModelClass.UserData
import com.example.usershow.RetrofitApiCall.ApiService
import com.example.usershow.RetrofitApiCall.RetrofitInstance
import retrofit2.Call

class UserRepository {
    private val apiService: ApiService = RetrofitInstance.serviceBuilder(ApiService::class.java)

    fun getUsers(): Call<UserData> {
        return apiService.getUsers()
    }
}
