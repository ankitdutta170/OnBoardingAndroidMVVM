package com.example.onboardingandroid.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onboardingandroid.models.UserItem

interface UserRepository {
    suspend fun addUser(userItem: UserItem)
    suspend fun deleteUser(email:String)
     fun getObservableLiveDataForUsers():LiveData<List<UserItem>>



}