package com.e.mood.view.ui.fragment.bottom_sheet

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.mood.R
import com.e.mood.viewmodel.ViewModelProviderFactory
import com.e.mood.viewmodel.bottom_sheet.CreateAccountViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton

class CreateAccountFragment : BottomSheetDialogFragment() {

    private lateinit var userName: EditText
    private lateinit var userSurname: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var signUpButton: MaterialButton

    private lateinit var viewModel: CreateAccountViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.bottom_sheet_create_account, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val providerFactory = ViewModelProviderFactory(context!!)
        viewModel = ViewModelProvider(this, providerFactory).get(CreateAccountViewModel::class.java)

        init()

        signUpButton.setOnClickListener {
            val mName: String = userName.editableText.toString().trim()
            val mSurname: String = userSurname.editableText.toString().trim()
            val mEmail: String = userEmail.editableText.toString().trim()
            val mPassword: String = userPassword.editableText.toString().trim()

            viewModel.singUp(mName, mSurname, mEmail, mPassword)

        }


        val progressDialog = ProgressDialog(context)


        viewModel.liveData.observe(this, Observer {result ->

            when(result){
                is CreateAccountViewModel.State.ShowLoading -> {
                    progressDialog.show()
                    progressDialog.setContentView(R.layout.progress_dialog)
                }

                is CreateAccountViewModel.State.HideLoading -> {
                    progressDialog.dismiss()
                }

                is CreateAccountViewModel.State.Result -> {
                    dismiss()
                }

            }
        })


    }

    fun init(){
        userName = view!!.findViewById(R.id.ET_create_account_name)
        userSurname = view!!.findViewById(R.id.ET_create_account_surname)
        userEmail = view!!.findViewById(R.id.ET_create_account_email)
        userPassword = view!!.findViewById(R.id.ET_create_account_password)
        signUpButton = view!!.findViewById(R.id.BTN_create_account_sign_up)
    }

}