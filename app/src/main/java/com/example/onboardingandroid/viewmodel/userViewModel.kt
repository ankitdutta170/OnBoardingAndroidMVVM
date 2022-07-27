package com.example.onboardingandroid.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.onboardingandroid.models.UserItem
import com.example.onboardingandroid.others.Constants
import com.example.onboardingandroid.others.Event
import com.example.onboardingandroid.others.Resource
import com.example.onboardingandroid.repository.DefaultUserRepository
import com.example.onboardingandroid.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class userViewModel(private val repo: UserRepository):ViewModel()
{
    init{

           // repo.getObservableLiveDataForUsers()

            viewModelScope.launch {
                repo.getUsers()
            }

    }

    private val _insertUserItemStatus = MutableLiveData<Event<Resource<UserItem>>>()
    val insertUserItemStatus: LiveData<Event<Resource<UserItem>>> = _insertUserItemStatus


    fun addUser(userItem: UserItem)=viewModelScope.launch(Dispatchers.IO){
        repo.addUser(userItem)

    }
    fun updateUser(email:String,userItem: UserItem)=viewModelScope.launch(Dispatchers.IO){
        repo.updateUser(email,userItem)

    }
    fun deleteUser(email: String)=viewModelScope.launch(Dispatchers.IO){
        repo.deleteUser(email)

    }

    fun insertUserItem(name:String,email:String,age:Int,phNo:Long){
        if(name.isEmpty() || email.isEmpty() || age.toString().isEmpty() || phNo.toString().isEmpty()){
            _insertUserItemStatus.postValue(Event(Resource.error("The fields must not be empty", null)))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH){
            _insertUserItemStatus.postValue(Event(Resource.error("The name of the user" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters", null)))
            return
        }
        val userItem = UserItem(1,age,email,name,phNo)
        addUser(userItem)
        _insertUserItemStatus.postValue(Event(Resource.success(userItem)))

    }
    val users: LiveData<List<UserItem>>
        get() = repo.getObservableLiveDataForUsers()
}