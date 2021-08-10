package com.example.proverka1.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proverka1.data.Resource
import com.example.proverka1.data.helper.PostHelper
import com.example.proverka1.data.helper.ProfileHelper
import com.example.proverka1.data.model.Post
import com.example.proverka1.data.model.User

class ProfileViewModel (private val profileHelper: ProfileHelper, private val postHelper: PostHelper) : ViewModel() {
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

    private var mutablePosts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val posts: LiveData<Resource<List<Post>>> get() = mutablePosts

    fun getCurrentUsersPosts() {
        mutablePosts.value = Resource.loading()
        postHelper.getCurrentUsersPosts(
            {
                mutablePosts.value = Resource.success(it)
            },
            {
                mutablePosts.value = Resource.error(it)
            }
        )
    }

}