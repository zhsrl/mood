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
import com.e.mood.view.ui.fragment.SignedFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragmentViewModel(val context: Context): ViewModel() {

    private var myContext = FragmentActivity()
    val liveData = MutableLiveData<State>()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInUser(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
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

            Toast.makeText(context, "Логин успешно выполнен!", Toast.LENGTH_SHORT).show()
//            val selectedFragment: SignedFragment = SignedFragment()
//            val fragmentManager: FragmentManager = myContext.supportFragmentManager
//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, selectedFragment)
//                    .disallowAddToBackStack()
//                    .commit()
        }else{
//            Toast.makeText(context,"Что-то пошло не так...", Toast.LENGTH_SHORT).show()
        }
    }



    sealed class State(){
        object ShowLoading: State()
        object HideLoading: State()
    }
}