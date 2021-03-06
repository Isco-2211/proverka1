package com.example.proverka1.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proverka1.data.Resource
import com.example.proverka1.data.helper.PostHelper

class AddPostViewModel (private val postHelper: PostHelper) :ViewModel() {

    private var mutablePost:MutableLiveData<Resource<String>> = MutableLiveData()
    val post: LiveData<Resource<String>>
    get() = mutablePost
    fun sendNewPost(byteArray: ByteArray, description: String) {
        mutablePost.value = Resource.loading()
        postHelper.sendNewPost(byteArray, description,
            {
                mutablePost.value = Resource.success("success")
            },
            {
                mutablePost.value = Resource.error(it)
            }


            )
    }

}