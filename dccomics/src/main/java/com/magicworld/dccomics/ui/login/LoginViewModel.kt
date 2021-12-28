package com.magicworld.dccomics.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicworld.dccomics.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    private val loginRepository = LoginRepository()

    private var userLogin: MutableLiveData<String> = MutableLiveData()
    val onUserLoggedIn: LiveData<String> = userLogin

    fun login(email: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {
            userLogin.postValue(loginRepository.signInUser(email, password))
        }
    }
}