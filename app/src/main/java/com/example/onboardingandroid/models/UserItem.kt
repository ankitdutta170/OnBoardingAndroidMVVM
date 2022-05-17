package com.example.onboardingandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserItem (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val age:Int,
    val email:String,
    val name:String,
    val ph_no:Long

        )

