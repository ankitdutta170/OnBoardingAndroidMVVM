package com.example.onboardingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_profile.*

class ShowProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_profile)

        //val bundle:Bundle? = intent.extras
        val name:String? = intent.getStringExtra("name")
        val age:String? = intent.getStringExtra("age")
        val phNo:String? = intent.getStringExtra("phNo")
        val email:String? = intent.getStringExtra("email")

        name_InProfile.text = name
        age_InProfile.text = age
        ph_no_InProfile.text = phNo
        email_Id_InProfile.text = email


    }
}