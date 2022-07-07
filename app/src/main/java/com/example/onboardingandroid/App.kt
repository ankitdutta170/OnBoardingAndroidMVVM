package com.example.onboardingandroid

import android.app.Application
import android.content.Context
import com.example.onboardingandroid.apis.RetrofitHelper
import com.example.onboardingandroid.apis.UserService
import com.example.onboardingandroid.db.UserDatabase
import com.example.onboardingandroid.repository.DefaultUserRepository
import com.example.onboardingandroid.repository.UserRepository

class App(val context: Context):Application() {
    lateinit var repo:UserRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val service = RetrofitHelper.getInstance().create(UserService::class.java)
        val db = UserDatabase.getDatabase(context)
        repo = DefaultUserRepository(service,db,context)
    }
}