package com.e.mood.view.ui.fragment.bottom_sheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.e.mood.R
import com.e.mood.viewmodel.ViewModelProviderFactory
import com.e.mood.viewmodel.bottom_sheet.SignInViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton

class SignInBottomFragment: BottomSheetDialogFragment() {


    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var signInButton: MaterialButton
    private lateinit var signUp: TextView

    private var viewModel: SignInViewModel? = context?.let { SignInViewModel(it) }
    private var createAccount: CreateAccountFragment = CreateAccountFragment()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.bottom_sheet_sign_in, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val providerFactory = ViewModelProviderFactory(context!!)
        viewModel = ViewModelProvider(this, providerFactory).get(SignInViewModel::class.java)

        init()

        signInButton.setOnClickListener {
            var mEmail: String = userEmail.editableText.toString().trim()
            var mPassword: String = userPassword.editableText.toString().trim()

            viewModel!!.signInUser(mEmail, mPassword)
        }
        signUp.setOnClickListener {
            createAccount.show(fragmentManager!!, "CREATE_ACCOUNT")
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                    bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }


    private fun init(){
        userEmail = view!!.findViewById(R.id.ET_bottom_sheet_sign_in_email)
        userPassword = view!!.findViewById(R.id.ET_bottom_sheet_sign_in_password)
        signInButton = view!!.findViewById(R.id.BTN_bottom_sheet_sign_in)
        signUp = view!!.findViewById(R.id.TV_create_account)
    }
}