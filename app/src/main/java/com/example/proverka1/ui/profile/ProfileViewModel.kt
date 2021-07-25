package com.example.proverka1.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proverka1.data.Resource
import com.example.proverka1.data.helper.ProfileHelper
import com.example.proverka1.data.model.User

class ProfileViewModel (private val profileHelper: ProfileHelper) : ViewModel() {
    private var mutableProfile: MutableLiveData<Resource<User>> = MutableLiveData()
    val profile: LiveData<Resource<User>>
    get() = mutableProfile


    fun getProfileData() {
        mutableProfile.value = Resource.loading()
        profileHelper.getProfileData(
            {
                mutableProfile.value = Resource.success(it)
            },
            {
                mutableProfile.value = Resource.error(it)
            }
        )
    }

}