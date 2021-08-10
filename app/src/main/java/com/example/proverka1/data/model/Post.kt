package com.example.proverka1.data.model

data class Post(
    val id: String = "",
    val userId: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val createDate: Long=System.currentTimeMillis(),
    val likes: Long=0,
    val views: Long=0
)