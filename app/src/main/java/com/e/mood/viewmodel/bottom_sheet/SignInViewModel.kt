package com.e.mood.viewmodel.bottom_sheet

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.mood.R
import com.e.mood.view.ui.fragment.ProfileFragment
import com.e.mood.view.ui.fragment.SignedFragment
import com.e.mood.view.ui.fragment.bottom_sheet.SignInBottomFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.logging.Handler

class SignInViewModel(val context: Context): ViewModel() {

    val liveData = MutableLiveData<State>()
    var handler: android.os.Handler = android.os.Handler()

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInUser(email: String, password: String){
        liveData.value = State.ShowLoading
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            val user: FirebaseUser = mAuth.currentUser!!

                            updateUI(user)
                            Toast.makeText(context, "Успешно вошли в систему!", Toast.LENGTH_SHORT)
                                .show()
                            liveData.value = State.HideLoading
                            liveData.postValue(State.Result(true))
                        } else {
                            liveData.value = State.HideLoading
                            Toast.makeText(context, p0.exception.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })
    }

    fun updateUI(user: FirebaseUser){

        val fragmentManager: FragmentManager = (context as FragmentActivity).supportFragmentManager


        fragmentManager.beginTransaction()
            .detach(ProfileFragment())
            .replace(R.id.fragment_container, SignedFragment())
            .disallowAddToBackStack()
            .commit()
    }

    sealed class State{
        object HideLoading: State()
        object ShowLoading: State()
        data class Result(val isSuccess: Boolean): State()
    }
}