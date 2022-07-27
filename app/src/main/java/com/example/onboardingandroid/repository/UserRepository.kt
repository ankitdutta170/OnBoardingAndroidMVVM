package com.example.onboardingandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onboardingandroid.models.UserItem

interface UserRepository {
    suspend fun addUser(userItem: UserItem)
    suspend fun deleteUser(email:String)
    suspend fun getUsers()
     fun getObservableLiveDataForUsers():LiveData<List<UserItem>>
     suspend fun updateUser(email:String,userItem: UserItem)



}