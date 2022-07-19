package com.example.onboardingandroid.db


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest

import com.example.onboardingandroid.getOrAwaitValue
import com.example.onboardingandroid.models.UserItem
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao:UserDao


    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userDao()
    }
    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun insertUser() = runBlockingTest {
        val userItem = UserItem(1,23,"ankit@gmail.com","Ankit",1234567890)
        dao.addUser(userItem)

        val allUserItems = dao.observeAllUserItems().getOrAwaitValue()

        Truth.assertThat(allUserItems).contains(userItem)

    }

    @Test
    fun deleteUser() = runBlockingTest {
        val userItem = UserItem(1,23,"ankit@gmail.com","Ankit",1234567890)
        dao.addUser(userItem)
        dao.deleteUserItem(userItem)

        val allUserItems = dao.observeAllUserItems().getOrAwaitValue()

        Truth.assertThat(allUserItems).doesNotContain(userItem)

    }





}