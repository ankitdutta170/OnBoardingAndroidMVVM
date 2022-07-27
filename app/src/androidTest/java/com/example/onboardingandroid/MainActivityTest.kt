package com.example.onboardingandroid

import android.widget.Button
import android.widget.EditText
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.annotation.UiThreadTest
import androidx.test.runner.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
@ExperimentalCoroutinesApi
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest{


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule @JvmField
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    lateinit var email:EditText
    lateinit var name:EditText
    lateinit var age:EditText
    lateinit var phNo:EditText
    lateinit var addButton:Button


    @Before
    fun setUp(){
        email = mActivityRule.activity.findViewById(R.id.edit_email)
        name = mActivityRule.activity.findViewById(R.id.edit_name)
        age = mActivityRule.activity.findViewById(R.id.edit_age)
        phNo = mActivityRule.activity.findViewById(R.id.edit_phno)
        addButton = mActivityRule.activity.findViewById(R.id.btn_add)


    }


    @Test
    fun testEmailTextFocus()= runBlockingTest{
        assertNotNull(email)
        assertTrue(email.requestFocus())
        assertTrue(email.hasFocus())


    }
    @Test
    fun testInvalidEmail(){
        email.requestFocus()
        assertEquals("",email.text.toString())
    }
    @Test
    fun testNameTextFocus(){
        Thread(Runnable{
            mActivityRule.runOnUiThread(Runnable {
                assertNotNull(name)
                assertTrue(name.requestFocus())
                assertTrue(name.hasFocus())
            })

        }).start()
    }
    @Test
    fun testInvalidName(){
        Thread(Runnable {
            mActivityRule.runOnUiThread {
                name.requestFocus()
                assertEquals("", name.text.toString())
            }
        })

    }
    @Test
    fun testAgeTextFocus(){
        Thread(Runnable {
            mActivityRule.runOnUiThread {
                assertNotNull(age)
                assertTrue(age.requestFocus())
                assertTrue(age.hasFocus())
            }
        })
    }
    @Test
    fun testInvalidAge(){
        Thread(Runnable {
            mActivityRule.runOnUiThread {
                age.requestFocus()
                assertEquals("", age.text.toString())
            }
        })
    }
    @Test
    fun testPhNoTextFocus(){
        Thread(Runnable {
            mActivityRule.runOnUiThread {
                assertNotNull(phNo)
                assertTrue(phNo.requestFocus())
                assertTrue(phNo.hasFocus())
            }
        })
    }
    @Test
    fun testInvalidPhNo(){
       Thread(Runnable  {
           mActivityRule.runOnUiThread(
               {
                   phNo.requestFocus()
                   assertEquals("", phNo.text.toString())
               },
           )

       })
    }







}