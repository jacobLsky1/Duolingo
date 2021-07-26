package com.jacoblip.andriod.duolingo.Views

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jacoblip.andriod.duolingo.Data.models.User
import com.jacoblip.andriod.duolingo.MainActivity
import com.jacoblip.andriod.duolingo.R
import com.jacoblip.andriod.duolingo.Data.models.LearningMatirial
import com.jacoblip.andriod.duolingo.utilities.Util
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class SignUpFragment(context: Context):Fragment() {

    lateinit var textfullName: TextInputEditText
    lateinit var textEmail: TextInputEditText
    lateinit var textEmailPassword: TextInputEditText
    lateinit var submitButton: Button
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up,container,false)
        view.apply {
            submitButton = findViewById(R.id.submitButton)
            textfullName = findViewById(R.id.userFullName)
            textEmail = findViewById(R.id.userEmail)
            textEmailPassword = findViewById(R.id.userEmailPassword)


            firebaseAuth = FirebaseAuth.getInstance()

            submitButton.setOnClickListener { submitFun() }
        }
        return view
    }

    fun submitFun(){


        val name = textfullName.text.toString().trim()
        val email = textEmail.text.toString().trim()
        val password = textEmailPassword.text.toString().trim()
        var error = 0



        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            error = 1
        }

        when (error) {
            1 -> {
                if(TextUtils.isEmpty(name) )
                    textfullName.error = "must not be empty"
                if(TextUtils.isEmpty(email))
                    textEmailPassword.error = "must not be empty"
                if(TextUtils.isEmpty(password))
                    textEmailPassword.error = "must not be empty"
            }
        }

        if(error!=1){
            view?.let { signIn(it,email,password,name) }
        }
    }

    fun signIn(view: View, email: String, password: String, name:String){
        showMessage(view,"Authenticating...")

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                Log.i("Firebase",firebaseAuth.currentUser.uid)
                val userID = firebaseAuth.currentUser.uid
                initUser(name,userID,email,password)
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()

            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        }

    }

    fun initUser(name: String, userID:String, email: String, password: String){
        val dbRefImages = FirebaseDatabase.getInstance().getReference("appImages")
        dbRefImages.setValue(Util.imgUrls)
        val dbRef = FirebaseDatabase.getInstance().getReference("users")
        val user = userID?.let { User(it,name,"",email,"",password,0,1,-1,0,1,true,SystemClock.currentThreadTimeMillis().toString(),LocalDate.now().toString(),"Hebrew",1) }

        dbRef.child(userID).setValue(user)
                        .addOnCompleteListener {task ->
                            if(task.isSuccessful) {
                              //  Toast.makeText(requireContext(), "new User formed", Toast.LENGTH_LONG).show()
                                Log.i("signup","success")
                            }else{
                                view?.let { showMessage(it,"Error: ${task.exception?.message}") }
                            }
                        }


        var learningData = LearningMatirial.learningMatirial
        var dbr = FirebaseDatabase.getInstance().getReference("lessons")
            dbr.setValue(learningData)
                .addOnCompleteListener {task ->
                    if(task.isSuccessful) {
                        //  Toast.makeText(requireContext(), "new User formed", Toast.LENGTH_LONG).show()
                        Log.i("learningMatirial","success")
                    }else{
                        view?.let { showMessage(it,"Error: ${task.exception?.message}") }
                    }
                }


    }

    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    companion object{
        fun newInstance(context: Context):SignUpFragment{
            return SignUpFragment(context)
        }
    }
}
