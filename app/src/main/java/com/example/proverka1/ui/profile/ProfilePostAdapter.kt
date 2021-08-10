package com.example.proverka1.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proverka1.R
import com.example.proverka1.data.model.Post
import com.example.proverka1.databinding.ItemPostProfileBinding

class ProfilePostAdapter : RecyclerView.Adapter<ProfilePostAdapter.ProfilePostViewHolder>() {

    var models: List<Post> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ProfilePostViewHolder(private var binding: ItemPostProfileBinding) : RecyclerView.ViewHolder(binding.root){
        fun populateModel(post: Post) {
            Glide.with(binding.root.context)
                .load(post.imageUrl)
                .centerCrop()
                .into(binding.postImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilePostViewHolder {
        val binding = ItemPostProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfilePostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfilePostViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size

}
