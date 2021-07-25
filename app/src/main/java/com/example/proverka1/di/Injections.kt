package com.example.proverka1.di

import com.example.proverka1.data.helper.AuthHelper
import com.example.proverka1.data.Settings
import com.example.proverka1.data.helper.ProfileHelper
import com.example.proverka1.ui.auth.signin.SignInViewModel
import com.example.proverka1.ui.auth.signup.SignUpViewModel
import com.example.proverka1.ui.profile.ProfileViewModel
import com.example.proverka1.ui.profile.edit.EditProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { AuthHelper(get(), get()) }
    single { Settings(androidContext()) }
    single { ProfileHelper(get(), get()) }
}

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
}
