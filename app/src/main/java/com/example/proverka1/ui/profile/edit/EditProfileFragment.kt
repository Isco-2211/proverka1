package com.example.proverka1.ui.profile.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.proverka1.R
import com.example.proverka1.data.ResourceState
import com.example.proverka1.data.model.User
import com.example.proverka1.databinding.FragmentProfileEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditProfileFragment:Fragment(R.layout.fragment_profile_edit) {
    private lateinit var binding : FragmentProfileEditBinding
    private val viewModel: EditProfileViewModel by viewModel()
    private lateinit var navController: NavController
    private lateinit var cUser : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCurrentUser()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileEditBinding.bind(view)
        navController = findNavController()
        setUpObservers()
        binding.apply {
            cancelBtn.setOnClickListener {
                navController.popBackStack()
            }
            doneBtn.setOnClickListener {
                cUser.biography = etBiography.text.toString()
                cUser.name = editName.text.toString()
                viewModel.editProfile(cUser)
            }
        }
    }

    private fun setUpObservers() {
        viewModel.profileEdit.observe(viewLifecycleOwner) {
            when(it.status) {
                ResourceState.LOADING -> {
                    binding.loading.isVisible = true
                }
                ResourceState.SUCCESS -> {
                    binding.loading.isVisible = false
                        Toast.makeText(requireContext(), "Your profile successfully updated", Toast.LENGTH_SHORT).show()
                }
                ResourceState.ERROR -> {
                    binding.loading.isVisible = false
                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
        }
    }
}