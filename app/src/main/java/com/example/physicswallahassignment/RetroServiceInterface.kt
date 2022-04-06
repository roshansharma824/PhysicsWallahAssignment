package com.example.physicswallahassignment


import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {

    @GET("users")
    fun getUsersList(): Call<UserData>
}