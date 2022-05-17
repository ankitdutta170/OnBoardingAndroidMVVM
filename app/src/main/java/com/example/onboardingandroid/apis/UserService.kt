package com.example.onboardingandroid.apis

import com.example.onboardingandroid.models.UserItem
import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserItem>>

    @GET("users/{email}")
    suspend fun getUser(@Path("email")email: String):Response<UserItem?>

    @POST("users")
    suspend fun addUser(@Body userItem: UserItem):Response<UserItem?>

    @PUT("users/{email}")
    suspend fun updateUser(@Path("email") email: String,@Body userItem: UserItem):Response<UserItem>

    @DELETE("users/{email}")
    suspend fun deleteUser(@Path("email") email:String):Response<UserItem>
}