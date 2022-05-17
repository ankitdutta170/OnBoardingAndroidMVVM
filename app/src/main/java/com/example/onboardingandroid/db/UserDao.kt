package com.example.onboardingandroid.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.onboardingandroid.models.UserItem

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUsers(user:List<UserItem>)

    @Query("select * from user")
    suspend fun getUsers():List<UserItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(userItem: UserItem)
}