package com.example.usershow.RetrofitApiCall

import com.example.usershow.ModelClass.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
     fun getUsers(): Call<UserData>
}