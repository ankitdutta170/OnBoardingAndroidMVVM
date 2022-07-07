package com.example.onboardingandroid

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onboardingandroid.adapter.MyAdapter
import com.example.onboardingandroid.apis.RetrofitHelper
import com.example.onboardingandroid.apis.UserService
import com.example.onboardingandroid.db.UserDatabase
import com.example.onboardingandroid.repository.DefaultUserRepository
import com.example.onboardingandroid.repository.UserRepository
import com.example.onboardingandroid.viewmodel.userViewModel
import com.example.onboardingandroid.viewmodel.userViewModelFactory
import kotlinx.android.synthetic.main.activity_show_user.*
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

class ShowUserActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var layoutManager: LinearLayoutManager
    var context = this
    lateinit var  vm:userViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)



        recView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recView.layoutManager=layoutManager


        //val service = RetrofitHelper.getInstance().create(IPostService::class.java)
        //val repo = App(this).repo

        val service = RetrofitHelper.getInstance().create(UserService::class.java)
        val db = UserDatabase.getDatabase(this)
        var repo = DefaultUserRepository(service,db,this)

        vm = ViewModelProvider(this,userViewModelFactory(repo)).get(userViewModel::class.java)

        vm.users.observe(this, Observer {
            Toast.makeText(this,"Number of records: ${it.size}", Toast.LENGTH_LONG).show()
            myAdapter = MyAdapter(this,it)
            myAdapter.setList(it)
            recView.adapter = myAdapter
        })
    }

}