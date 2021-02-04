package com.e.mood.view.ui.fragment

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.mood.R
import com.e.mood.view.ui.fragment.bottom_sheet.CreateAccountFragment
import com.e.mood.view.ui.fragment.bottom_sheet.SignInBottomFragment
import com.e.mood.viewmodel.ViewModelProviderFactory
import com.e.mood.viewmodel.fragment.ProfileFragmentViewModel
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment() : Fragment() {

    lateinit var signOut: Button

    private var handler: Handler = Handler()

    private lateinit var viewModel: ProfileFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val providerFactory = ViewModelProviderFactory(context!!)
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileFragmentViewModel::class.java)

        signOut = view!!.findViewById(R.id.BTN_profile_sign_out)

        signOut.setOnClickListener {
            viewModel.signOutUser()
        }



        viewModel.liveData.observe(this, Observer {
            val progressDialog = ProgressDialog(context)

            when(it){
                is ProfileFragmentViewModel.State.HideLoading -> {
                    progressDialog.dismiss()
                }
                is ProfileFragmentViewModel.State.ShowLoading -> {

                    handler.postDelayed(Runnable {
                        kotlin.run {
                            progressDialog.show()
                            progressDialog.setContentView(R.layout.progress_dialog)

                            val signInBottomSheet: SignInBottomFragment = SignInBottomFragment()
                            signInBottomSheet.show(fragmentManager!!, "SIGN_IN")
                        }
                    }, 2000)
                }
            }
        })




    }



}