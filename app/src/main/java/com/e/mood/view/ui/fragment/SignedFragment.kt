package com.e.mood.view.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.e.mood.R
import com.google.firebase.auth.FirebaseAuth


class SignedFragment : Fragment() {

    lateinit var signOut: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_signed, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        signOut = view!!.findViewById(R.id.signOut)

        signOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()

            var handler: Handler = Handler()
            val progressDialog = ProgressDialog(context)


            progressDialog.show()
            progressDialog.setContentView(R.layout.progress_dialog)
            handler.postDelayed(Runnable {

                kotlin.run {
                    fragmentManager!!.beginTransaction()
                        .detach(SignedFragment())
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()

                    progressDialog.dismiss()
                }
            }, 3000)

        }
    }

}