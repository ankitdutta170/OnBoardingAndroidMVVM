package com.example.onboardingandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.onboardingandroid.apis.RetrofitHelper
import com.example.onboardingandroid.apis.UserService
import com.example.onboardingandroid.db.UserDatabase
import com.example.onboardingandroid.models.UserItem
import com.example.onboardingandroid.repository.UserRepository
import com.example.onboardingandroid.viewmodel.userViewModel
import com.example.onboardingandroid.viewmodel.userViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    lateinit var  vm:userViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelInitializer()




        btn_add.setOnClickListener {
            if(!isValidEmail(edit_email.text.toString())){
                Toast.makeText(this,"Enter valid email",Toast.LENGTH_LONG).show()
            }
            else if(!isValidPhone(edit_phno.text.toString())){
                Toast.makeText(this,"Enter valid phone",Toast.LENGTH_LONG).show()
            }
            else{
                var user = UserItem(0,Integer.parseInt(edit_age.text.toString()),
                    edit_email.text.toString(),edit_name.text.toString(),edit_phno.text.toString().toLong())
                vm.addUser(user)
            }

           // Toast.makeText(this,"Add button clicked",Toast.LENGTH_LONG).show()


        }
        btn_update.setOnClickListener {
            var user = UserItem(0,Integer.parseInt(edit_age.text.toString()),
                edit_email.text.toString(),edit_name.text.toString(),edit_phno.text.toString().toLong())
            vm.updateUser(edit_email.text.toString(),user)
        }
        btn_delete.setOnClickListener {
            var email = edit_email.text.toString()
            vm.deleteUser(email)
        }

        btn_show.setOnClickListener {
            val intent = Intent(this,ShowUserActivity::class.java)
            startActivity(intent)
        }
    }

    private fun viewModelInitializer() {
        val service = RetrofitHelper.getInstance().create(UserService::class.java)
        val db = UserDatabase.getDatabase(this)
        var repo = UserRepository(service,db,this)
        vm = ViewModelProvider(this, userViewModelFactory(repo)).get(userViewModel::class.java)

        repo.getUserLiveDataForInsert().observe(this, Observer<UserItem?>{
            if(it == null){
                Toast.makeText(this,"Failed to add User",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Successfully added User",Toast.LENGTH_LONG).show()
            }
        })

        repo.getUserLiveDataForUpdate().observe(this, Observer<UserItem?>{
            if(it == null){
                Toast.makeText(this,"Failed to update User",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Successfully updated User",Toast.LENGTH_LONG).show()
            }
        })
        repo.getUserLiveDataForDelete().observe(this, Observer<UserItem?>{
            if(it == null){
                Toast.makeText(this,"Failed to delete User",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Successfully deleted User",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPhone(phone:String):Boolean{
        if(!Pattern.matches("^[0-9]{10,13}$", phone)) {
            return phone.length > 6 && phone.length <= 13;
        }
        return false
    }
}