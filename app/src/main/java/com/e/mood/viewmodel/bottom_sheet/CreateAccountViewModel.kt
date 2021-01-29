package com.e.mood.viewmodel.bottom_sheet

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.mood.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CreateAccountViewModel(val context: Context): ViewModel() {

    val liveData = MutableLiveData<State>()

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    var mDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()

    var isSuccess: Boolean = false

    fun singUp(name: String, surname: String, email: String, password: String){
        liveData.value = State.ShowLoading
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if(p0.isSuccessful){

                        val user = User(name = name,
                            surname = surname,
                            email = email,
                            password = password)

                        mDatabase.getReference("Users")
                            .child(mAuth.currentUser!!.uid)
                            .setValue(user)
                            .addOnCompleteListener(object : OnCompleteListener<Void>{
                                override fun onComplete(p0: Task<Void>) {
                                    if(p0.isSuccessful){
                                        liveData.value = State.HideLoading
                                        Toast.makeText(context, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show()

                                        // Checking variable for dismiss Bottom Sheet Layout
                                        isSuccess = true


                                    }else{
                                        liveData.value = State.HideLoading
                                        Toast.makeText(context, p0.exception.toString(), Toast.LENGTH_SHORT).show()
                                        Log.e("DB_AUTH_ERROR", p0.exception.toString())
                                        isSuccess = false
                                    }
                                }
                            })

                    }else{
                        liveData.value = State.HideLoading
                        Toast.makeText(context, p0.exception.toString(), Toast.LENGTH_SHORT).show()
                        Log.e("AUTH_ERROR", p0.exception.toString())
                        isSuccess = false
                    }
                }
            })

    }

    sealed class State(){
        object HideLoading: State()
        object ShowLoading: State()
    }
}