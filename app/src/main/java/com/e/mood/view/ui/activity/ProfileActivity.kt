package com.e.mood.view.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.e.mood.R
import com.e.mood.viewmodel.ProfileActivityViewModel
import com.e.mood.viewmodel.ViewModelProviderFactory
import com.google.android.material.button.MaterialButton

class ProfileActivity() : AppCompatActivity() {

    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var signInButton: MaterialButton


    private lateinit var viewModel: ProfileActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val providerFactory = ViewModelProviderFactory(this)
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileActivityViewModel::class.java)

        init()

        var window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.top_bar_color)

        signInButton.setOnClickListener {
            var mEmail = userEmail.editableText.toString().trim()
            var mPassword = userPassword.editableText.toString().trim()

            viewModel.signInUser(mEmail, mPassword)
        }
    }

    fun init(){
        userEmail = findViewById(R.id.ET_profile_sign_in_email)
        userPassword = findViewById(R.id.ET_profile_sign_in_password)
        signInButton = findViewById(R.id.BTN_profile_sign_in)
    }
}