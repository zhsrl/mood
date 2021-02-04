package com.e.mood.viewmodel.fragment

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.mood.R
import com.e.mood.view.ui.activity.MainActivity
import com.e.mood.view.ui.fragment.ProfileFragment
import com.e.mood.view.ui.fragment.SignedFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragmentViewModel(val context: Context): ViewModel() {

    val liveData = MutableLiveData<State>()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun signOutUser(){
        liveData.value = State.ShowLoading
        mAuth.signOut()
        liveData.value = State.HideLoading
    }


    sealed class State{
        object ShowLoading: State()
        object HideLoading: State()
    }
}