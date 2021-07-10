package com.example.proverka1.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.proverka1.R
import com.example.proverka1.data.Settings
import com.example.proverka1.databinding.FragmentSplashBinding

class SplashFragment: Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        requireActivity().actionBar?.hide()
        binding.lottieView.setMaxProgress(0.6f)
        val settings = Settings(requireContext())

        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                if (settings.signedIn) {

                } else {

                }
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })

    }
}