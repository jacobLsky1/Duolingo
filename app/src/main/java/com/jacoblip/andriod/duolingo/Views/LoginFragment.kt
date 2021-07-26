package com.jacoblip.andriod.duolingo.Views

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jacoblip.andriod.duolingo.MainActivity
import com.jacoblip.andriod.duolingo.R

class LoginFragment :Fragment(){
    lateinit var textEmail: TextInputEditText
    lateinit var textEmailPassword: TextInputEditText
    lateinit var submitButton: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login,container,false)
        view.apply {
            submitButton = findViewById(R.id.submitButton)
            textEmail = findViewById(R.id.userEmail)
            textEmailPassword = findViewById(R.id.userEmailPassword)


            firebaseAuth = FirebaseAuth.getInstance()

            submitButton.setOnClickListener { submitFun() }
        }
        return view
    }

    fun submitFun(){

        val email = textEmail.text.toString().trim()
        val password = textEmailPassword.text.toString().trim()
        var error = 0



        if ( TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            error = 1
        }

        if(error!=1){
            view?.let { signIn(it,email,password) }
        }


        when (error) {
            1 -> {
                if(TextUtils.isEmpty(email))
                    textEmailPassword.error = "must not be empty"
                if(TextUtils.isEmpty(password))
                    textEmailPassword.error = "must not be empty"
            }
        }
    }

    fun signIn(view: View, email: String, password: String){
        showMessage(view,"Authenticating...")

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Log.i("Firebase",firebaseAuth.currentUser.uid)
                val userID = firebaseAuth.currentUser.uid
                upDatelogin(userID)
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()

            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        }

    }

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    fun upDatelogin(userId:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("users")


    }

    companion object{
        fun newInstance():LoginFragment{
            return LoginFragment()
        }
    }
}
