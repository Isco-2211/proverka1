package com.example.proverka1.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.proverka1.R
import com.example.proverka1.data.Settings
import com.example.proverka1.databinding.FragmentSplashBinding

class SplashFragment: Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding = FragmentSplashBinding.bind(view)
        requireActivity().actionBar?.hide()
        binding.lottieView.setMaxProgress(0.6f)
        val settings = Settings(requireContext())

        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {

                if (settings.signedIn) {
                    navController.navigate(R.id.action_splashFragment_to_mainFragment)
                } else {
                    navController.navigate(R.id.action_splashFragment_to_signInFragment)
                }
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })

    }
}