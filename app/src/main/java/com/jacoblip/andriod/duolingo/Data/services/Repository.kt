package com.jacoblip.andriod.duolingo.Data.services

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jacoblip.andriod.duolingo.Data.models.User
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Repository(context: Context) {

    var theUser:MutableLiveData<User> = MutableLiveData()


    //could not return the user to the view model
/*
    fun getUser():MutableLiveData<User>{
            Log.i("repository", FirebaseAuth.getInstance().currentUser!!.uid)
            var ref = FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)

            val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    theUser.postValue(dataSnapshot.getValue(User::class.java))
                    Log.i("repository", theUser.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

        return theUser
    }

 */

    fun updateUser(user:User){

    }

}