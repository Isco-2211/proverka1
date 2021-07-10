package com.example.proverka1.ui.auth.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.proverka1.R
import com.example.proverka1.data.ResourceState
import com.example.proverka1.databinding.FragmentSignInBinding
import com.example.proverka1.ui.auth.signup.SignUpFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment:Fragment(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModel()
    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navController = Navigation.findNavController(view)
        setObservers()
        binding.apply {
            btnSignUp.setOnClickListener {
                navController.navigate(R.id.action_signInFragment_to_signUpFragment)
            }
            btnSignIn.setOnClickListener {
                var success = true
                if(etEmail.text.isNullOrEmpty()) {
                    success = false
                    etEmail.error = getString(R.string.fill_the_field)
                }
                if (etPassword.text.isNullOrEmpty()) {
                    success = false
                    etPassword.error = getString(R.string.fill_the_field)
                }
                if (!success) return@setOnClickListener
                viewModel.signIn(etEmail.text.toString(), etPassword.text.toString())
            }
        }
    }

    private fun setObservers() {
        viewModel.signInStatus.observe(viewLifecycleOwner,{
            when(it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    navController.navigate(R.id.action_signInFragment_to_mainFragment)
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        )
    }

    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            etEmail.isEnabled = !isLoading
            etPassword.isEnabled = !isLoading
        }
    }

}