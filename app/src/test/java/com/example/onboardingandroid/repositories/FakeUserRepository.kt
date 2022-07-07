package com.example.onboardingandroid.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onboardingandroid.models.UserItem
import com.example.onboardingandroid.repository.UserRepository

class FakeUserRepository:UserRepository{
    private val userItems= mutableListOf<UserItem>()
    private val observableUserItems = MutableLiveData<List<UserItem>>(userItems)
    private var shouldReturnNetworkError = false

    fun shouldReturnNetworkError(value:Boolean){
        shouldReturnNetworkError = value

    }

    private fun refreshLiveData(){
        observableUserItems.postValue(userItems)
    }

    override suspend fun addUser(userItem: UserItem) {
        userItems.add(userItem)
        refreshLiveData()
    }

    override suspend fun deleteUser(email: String) {


    }

    override suspend fun getObservableLiveDataForUsers(): LiveData<List<UserItem>> {
        return observableUserItems
    }


}