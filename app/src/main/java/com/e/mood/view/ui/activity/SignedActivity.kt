
package com.e.mood.view.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.e.mood.R
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class SignedActivity : AppCompatActivity() {

    lateinit var signOut: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signed)

        signOut = findViewById(R.id.BTN_signOut)

        signOut.setOnClickListener {
            signOut()

        }
    }

    fun signOut(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(applicationContext, ProfileActivity::class.java)
        startActivity(intent)
        finish()
    }
}