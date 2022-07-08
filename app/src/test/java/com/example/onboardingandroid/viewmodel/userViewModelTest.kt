package com.example.onboardingandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.onboardingandroid.MainCoroutineRule
import com.example.onboardingandroid.getOrAwaitValueTest
import com.example.onboardingandroid.others.Constants
import com.example.onboardingandroid.others.Status
import com.example.onboardingandroid.repositories.FakeUserRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi


import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class userViewModelTest{
    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var userViewModel:userViewModel

    @Before
    fun setup(){
        userViewModel = userViewModel(FakeUserRepository())
    }

    @Test
    fun `insert user item with empty field, returns error`(){
        userViewModel.insertUserItem("","name@email.com",23,7903083839)

        val value = userViewModel.insertUserItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }
    @Test
    fun `insert user item with long name, returns error`(){
        val string = buildString {
            for(i in 1..Constants.MAX_NAME_LENGTH+1){
                append(1)

            }
        }

        userViewModel.insertUserItem(string,"name@email.com",23,7903083839)

        val value = userViewModel.insertUserItemStatus.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)

    }
}