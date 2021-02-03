package com.e.mood.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.mood.view.ui.activity.ProfileActivity
import com.e.mood.view.ui.activity.SignedActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileActivityViewModel(val context: Context): ViewModel() {

    val liveData = MutableLiveData<State>()

    private val activity: ProfileActivity = ProfileActivity()

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInUser(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if(p0.isSuccessful){
                        val currentUser: FirebaseUser = mAuth.currentUser!!

                        updateUI(currentUser)

                    }else{
                        Log.e("AUTH_ERROR", p0.exception.toString())
                    }
                }
            })
    }

    fun updateUI(user: FirebaseUser){
        if(user != null){
            val intent = Intent(context, SignedActivity::class.java)
            context.startActivity(intent)
            activity.finish()
        }else{
            Toast.makeText(activity, "User d signed", Toast.LENGTH_SHORT).show()
        }
    }

    sealed class State(){
        object HideLoading: State()
        object ShowLoading: State()
    }
}