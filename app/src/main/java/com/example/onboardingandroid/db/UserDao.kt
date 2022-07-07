package com.example.onboardingandroid.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.onboardingandroid.models.UserItem

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUsers(user:List<UserItem>)

    @Query("select * from user")
    suspend fun getUsers():List<UserItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userItem: UserItem)

    @Delete
    suspend fun deleteUserItem(user:UserItem)

    @Query("select * from user")
    fun observeAllUserItems():LiveData<List<UserItem>>
}