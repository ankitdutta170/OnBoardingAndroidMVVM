package com.example.onboardingandroid.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onboardingandroid.models.UserItem


@Database(entities = [UserItem::class],version = 2)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao():UserDao

    companion object{

        private var INSTANCE:UserDatabase?=null
        fun getDatabase(context: Context):UserDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,UserDatabase::class.java,"NEWDB").build()
                }
            }
            return INSTANCE!!
        }
    }



}