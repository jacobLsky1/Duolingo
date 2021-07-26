package com.jacoblip.andriod.duolingo

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jacoblip.andriod.duolingo.Data.services.*
import com.jacoblip.andriod.duolingo.Views.GreetingsFragment
import com.jacoblip.andriod.duolingo.Views.LoginFragment
import com.jacoblip.andriod.duolingo.Views.OfflineFragment
import com.jacoblip.andriod.duolingo.Views.SignUpFragment
import com.jacoblip.andriod.duolingo.utilities.Util
import com.jacoblip.andriod.duolingo.utilities.WifiReceiver
import io.realm.Realm


const val TAG = "UserActivity"
class SignUpVerificationActivity: AppCompatActivity(),GreetingsFragment.Callbacks {

    lateinit var viewModel: LogInViewModel
    lateinit var wifiReceiver: WifiReceiver
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Duolingo)
        setContentView(R.layout.activity_login)
        Realm.init(this)
        val repository = Repository(applicationContext)
        val viewModelProviderFactory = LogInViewModelProviderFactory(repository,applicationContext)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(LogInViewModel::class.java)
        wifiReceiver = WifiReceiver()

        setUpInternetObserver()
        seeIfLoggedIn()
    }

    fun seeIfLoggedIn(){

        FirebaseAuth.AuthStateListener() {
            fun onAuthStateChanged(@NonNull firebaseAuth:FirebaseAuth) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null)
                {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
    fun setUpInternetObserver(){
        // TODO: 4/18/2021 in the future we will need to see if the user is saved
        Util.hasInternet.observe(this, Observer { hasInternet ->
            if (hasInternet){
                fragment = GreetingsFragment.newInstance(applicationContext)
                setFragement(fragment)
            }
            if (!hasInternet&&Util.useAppWithInternet){
                fragment = OfflineFragment.newInstance()
                setFragement(fragment)
            }
        })
    }

    fun setFragement(fragment:Fragment){
        if(fragment is LoginFragment ||fragment is SignUpFragment) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment).addToBackStack(null)
                .commit()
        }else{
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }


    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(wifiReceiver, filter)
    }
    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiReceiver)
    }

    override fun onButtonSelected(fragment: Fragment) {
        setFragement(fragment)
    }

}