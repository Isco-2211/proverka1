package com.example.proverka1.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proverka1.data.FirebaseHelper
import com.example.proverka1.data.Resource

class SignInViewModel(private val firebaseHelper: FirebaseHelper) : ViewModel() {

    private val mutableSignInStatus: MutableLiveData<Resource<Any?>> = MutableLiveData()
    val signInStatus: LiveData<Resource<Any?>>
        get() = mutableSignInStatus

    fun signIn(email: String, password: String) {
        mutableSignInStatus.value = Resource.loading()
        firebaseHelper.signIn(email, password,
        {
            mutableSignInStatus.value = Resource.success(null)
        },
        {
            mutableSignInStatus.value = Resource.error(it)
        }
        )
    }

}