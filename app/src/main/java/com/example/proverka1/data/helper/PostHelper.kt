package com.example.proverka1.data.helper

import com.example.proverka1.data.N
import com.example.proverka1.data.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class PostHelper(private val db: FirebaseFirestore, private val auth: FirebaseAuth,
                private val storage: FirebaseStorage) {

    fun sendNewPost(byteArray: ByteArray, description: String, onSuccess: () -> Unit,
                    onFailure: (msg: String?) -> Unit) {

        val compressedRostRef = storage.reference.child("compressedPosts/${UUID.randomUUID()}")

        val uploadTask = compressedRostRef.putBytes(byteArray)
        uploadTask.addOnSuccessListener {
            compressedRostRef.downloadUrl.addOnSuccessListener {
                val post = Post(id=UUID.randomUUID().toString(),
                    imageUrl = it.toString(),
                    userId = auth.currentUser!!.uid, description = description)
                db.document("${N.POSTS}/${post.id}").set(post)
                    .addOnSuccessListener {
                        onSuccess.invoke()
                    }
                    .addOnFailureListener {
                        onFailure.invoke(it.localizedMessage)
                    }
            }

        }
            .addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }

    fun getCurrentUsersPosts(onSuccess: (posts: List<Post>) -> Unit,
        onFailure: (msg: String?) -> Unit) {
        db.collection(N.POSTS).whereEqualTo("userId", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener {
                val res = it.documents.map { doc->
                    doc.toObject(Post::class.java)!!
                }
                onSuccess.invoke(res)
            }
            .addOnFailureListener {
                onFailure.invoke(it.message)
            }
    }

}