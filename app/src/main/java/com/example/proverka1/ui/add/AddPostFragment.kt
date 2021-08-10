package com.example.proverka1.ui.add

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.proverka1.R
import com.example.proverka1.data.ResourceState
import com.example.proverka1.databinding.FragmentAddPostBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class AddPostFragment:Fragment(R.layout.fragment_add_post) {

    companion object {
        //image pick code
        private const val IMAGE_PICK_CODE = 1000
    }

    private val viewModel: AddPostViewModel by viewModel()
    private lateinit var binding : FragmentAddPostBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddPostBinding.bind(view)
        navController = findNavController()
        pickImageFromGallery()
        binding.apply {
            btnSend.setOnClickListener {
                ivPost.isDrawingCacheEnabled = true
                ivPost.buildDrawingCache()
                val bitmap = (ivPost.drawable as BitmapDrawable).bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                val data = baos.toByteArray()
                viewModel.sendNewPost(data, etDescription.text.toString())
            }
        }
        setUpObservers()

    }

    private fun setUpObservers () {
        viewModel.post.observe(viewLifecycleOwner) {
            when(it.status) {
                ResourceState.SUCCESS -> {
                    binding.loading.isVisible = false
                    navController.navigate(R.id.action_addPostFragment_to_homeFragment)
                }
                ResourceState.ERROR -> {
                    binding.loading.isVisible = false
                    Toast.makeText(requireContext(), it.message , Toast.LENGTH_SHORT).show()
                }
                ResourceState.LOADING -> {
                    binding.loading.isVisible = true
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){

            binding.ivPost.setImageURI(data?.data)

        }
    }
}