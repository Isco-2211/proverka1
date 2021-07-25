package com.example.proverka1.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proverka1.data.helper.AuthHelper
import com.example.proverka1.data.Resource

class SignUpViewModel(private val authHelper: AuthHelper) : ViewModel() {
    private var mutableSignUpStatus: MutableLiveData<Resource<String?>> = MutableLiveData()
    val signUpStatus: LiveData<Resource<String?>>
    get() = mutableSignUpStatus

    fun signUp(email: String, password: String) {
        mutableSignUpStatus.value = Resource.loading()
        authHelper.signUp(email, password,
            {
                addUserToDb()


            },
            {
                mutableSignUpStatus.value = Resource.error(it)
            })
    }

    private fun addUserToDb() {
        authHelper.addUserToDb(
            {
                mutableSignUpStatus.value = Resource.success(null)
            } ,
            {
                mutableSignUpStatus.value = Resource.error(it)
            })
    }

}