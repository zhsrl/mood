package com.e.mood.view.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.e.mood.R
import com.e.mood.view.ui.fragment.bottom_sheet.CreateAccountFragment


class ProfileFragment() : Fragment() {

    private var createAccountBottomSheet: CreateAccountFragment = CreateAccountFragment()
    private lateinit var createAccount: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createAccount = view!!.findViewById(R.id.TV_create_account)

        createAccount.setOnClickListener {
            createAccountBottomSheet.show(fragmentManager!!, "CREATE_ACCOUNT")
        }
    }

}