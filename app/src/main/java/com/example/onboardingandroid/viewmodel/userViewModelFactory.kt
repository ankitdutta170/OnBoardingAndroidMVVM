package com.example.onboardingandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.onboardingandroid.repository.DefaultUserRepository


class userViewModelFactory(private val repo:DefaultUserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return userViewModel(repo) as T
    }

}