package com.example.proverka1.ui.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.proverka1.R
import com.example.proverka1.data.ResourceState
import com.example.proverka1.databinding.FragmentProfileBinding
import com.example.proverka1.ui.main.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment:Fragment(R.layout.fragment_profile) {
    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding : FragmentProfileBinding
    private lateinit var parentNavController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        viewModel.getProfileData()
        setUpObservers()
        parentNavController = (parentFragment?.parentFragment as MainFragment).findNavController()
        binding.btnEdit.setOnClickListener {
            parentNavController.navigate(R.id.action_mainFragment_to_editProfileFragment)
        }

    }

    private fun setUpObservers () {
        viewModel.profile.observe(viewLifecycleOwner, {
            when(it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    setLoading(false)
                    binding.apply {
                        val u=it.data!!
                        tvUserName.text = u.email
                        tvName.text = u.name
                        tvBiography.text = u.biography
                        tvFollowersCount.text = u.followersCount.toString()
                        tvFollowingCount.text = u.followingsCount.toString()
                        tvPostCount.text = u.postCount.toString()
                        Glide.with(this@ProfileFragment)
                            .load(u.image)
                            .centerCrop()
                            .into(avatar)
                    }
                }
            }
        })
    }

    private fun setLoading(isLoading : Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            btnEdit.isEnabled = !isLoading
            rvPosts.isEnabled = !isLoading
            tvUserName.isEnabled = !isLoading
        }
    }

}