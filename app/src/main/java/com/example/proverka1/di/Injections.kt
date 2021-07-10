package com.example.proverka1.di

import com.example.proverka1.data.FirebaseHelper
import com.example.proverka1.data.Settings
import com.example.proverka1.ui.auth.signin.SignInViewModel
import com.example.proverka1.ui.auth.signup.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseHelper(get()) }
    single { Settings(androidContext()) }
}

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
}
