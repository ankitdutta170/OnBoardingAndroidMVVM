package com.example.onboardingandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onboardingandroid.repository.UserRepository

class userViewModelFactory(private val repo:UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return userViewModel(repo) as T
    }

}