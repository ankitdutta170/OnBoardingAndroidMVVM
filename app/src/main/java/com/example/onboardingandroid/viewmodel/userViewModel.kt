package com.example.onboardingandroid.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.onboardingandroid.models.UserItem
import com.example.onboardingandroid.repository.DefaultUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class userViewModel(private val repo:DefaultUserRepository):ViewModel()
{
    init{
        viewModelScope.launch {
            repo.getUsers()
        }
    }
    fun addUser(userItem: UserItem)=viewModelScope.launch(Dispatchers.IO){
        repo.addUser(userItem)

    }
    fun updateUser(email:String,userItem: UserItem)=viewModelScope.launch(Dispatchers.IO){
        repo.updateUser(email,userItem)

    }
    fun deleteUser(email: String)=viewModelScope.launch(Dispatchers.IO){
        repo.deleteUser(email)

    }
    val users: LiveData<List<UserItem>>
        get() = repo.users
}