package com.magicworld.dccomics.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicworld.dccomics.data.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val registerRepository =RegisterRepository()

    private var userCreate : MutableLiveData<String> = MutableLiveData()
    val onUserCreate: LiveData<String> = userCreate

    private var userRegister: MutableLiveData<String> = MutableLiveData()
    val onUserRegister: LiveData<String> = userRegister



    fun register(email: String, password: String) {
        GlobalScope.launch (Dispatchers.IO){
            userRegister.postValue(registerRepository.createUser(email,password))
        }
    }

    fun createUserAccount(email: String, username: String) {

        GlobalScope.launch (Dispatchers.IO){
            userCreate.postValue(registerRepository.createUserInDataBase(email,username))
        }
    }

}