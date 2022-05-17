package com.example.onboardingandroid.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.onboardingandroid.apis.UserService
import com.example.onboardingandroid.db.UserDatabase
import com.example.onboardingandroid.models.UserItem
import com.example.onboardingandroid.util.NetworkUtil

class UserRepository(private val userService: UserService,private val userDb: UserDatabase,context: Context) {
    val con = context
    private val userLiveDataForGetAll = MutableLiveData<List<UserItem>>()
    private val userLiveDataForInsert = MutableLiveData<UserItem?>()
    private val userLiveDataForUpdate = MutableLiveData<UserItem?>()
    private val userLiveDataForDelete = MutableLiveData<UserItem?>()

    fun getUserLiveDataForInsert():MutableLiveData<UserItem?>{
        return userLiveDataForInsert
    }
    fun getUserLiveDataForUpdate():MutableLiveData<UserItem?>{
        return userLiveDataForUpdate
    }

    fun getUserLiveDataForDelete():MutableLiveData<UserItem?>{
        return userLiveDataForDelete
    }
    suspend fun getUsers(){
        if(NetworkUtil.isConnected(con)){
            val results = userService.getUsers()
            if(results.body()!=null){
                userDb.userDao().addUsers(results.body()!!)
                userLiveDataForGetAll.postValue(results.body())
            }
        }
        else{
            val users = userDb.userDao().getUsers()
            userLiveDataForGetAll.postValue(users)
        }

    }

    suspend fun addUser(userItem: UserItem){
           if(NetworkUtil.isConnected(con)) {
                   val results = userService.addUser(userItem)
               if(results.body()!=null){
                   userLiveDataForInsert.postValue(results.body()!!)
               }
               else{
                   userLiveDataForInsert.postValue(null)
               }

           }
    }
    suspend fun updateUser(email:String,userItem: UserItem){
        if(NetworkUtil.isConnected(con)) {
            val results = userService.updateUser(email,userItem)
            if(results.body()!=null){
                userLiveDataForUpdate.postValue(results.body()!!)
            }
            else{
                userLiveDataForUpdate.postValue(null)
            }

        }
    }
    suspend fun deleteUser(email:String){
        if(NetworkUtil.isConnected(con)) {
            val results = userService.deleteUser(email)
            if(results.body()!=null){
                userLiveDataForDelete.postValue(results.body()!!)
            }
            else{
                userLiveDataForDelete.postValue(null)
            }

        }
    }
    val users: LiveData<List<UserItem>>
        get() = userLiveDataForGetAll

}





