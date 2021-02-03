package com.e.mood.viewmodel.bottom_sheet

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.mood.view.ui.fragment.bottom_sheet.SignInBottomFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInViewModel(val context: Context): ViewModel() {

    private val liveData = MutableLiveData<State>()
    private val sheet = SignInBottomFragment()
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInUser(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {
                        if(p0.isSuccessful){
                            val user: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
                            updateUI(user)

                        }else{
                            Toast.makeText(context, p0.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }

    fun updateUI(user: FirebaseUser){
        if(user != null){
        }else{
            Toast.makeText(context, "Didnt signed!", Toast.LENGTH_SHORT).show()
        }
    }


    sealed class State{
        object HideLoading: State()
        object ShowLoading: State()
    }
}