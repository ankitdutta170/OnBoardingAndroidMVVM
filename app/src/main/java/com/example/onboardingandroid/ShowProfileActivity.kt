package com.example.onboardingandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_profile.*

class ShowProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_profile)

        //val bundle:Bundle? = intent.extras
        var name:String? = intent.getStringExtra("name")
        var age:String? = intent.getStringExtra("age")
        var phNo:String? = intent.getStringExtra("phNo")
        var email:String? = intent.getStringExtra("email")


        nameInTitleProfile.text = name
        name_InProfile.text = name
        age_InProfile.text = age
        ph_no_InProfile.text = phNo
        email_Id_InProfile.text = email


    }
}