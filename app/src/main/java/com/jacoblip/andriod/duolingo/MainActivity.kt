package com.jacoblip.andriod.duolingo

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.Data.services.Repository
import com.jacoblip.andriod.duolingo.Data.services.ViewModelProviderFactory
import com.jacoblip.andriod.duolingo.Views.*
import com.jacoblip.andriod.duolingo.utilities.Util
import com.jacoblip.andriod.duolingo.utilities.WifiReceiver
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity(),ClassroomFragment.Callbacks {

    lateinit var viewModel:MainViewModel
    lateinit var wifiReceiver:WifiReceiver
    lateinit var fragment:Fragment
    lateinit var bottomNavigationMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Duolingo)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        var realmConfiguration = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(realmConfiguration)
        val repository = Repository(applicationContext)
        val viewModelProviderFactory = ViewModelProviderFactory(repository,applicationContext)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
        wifiReceiver = WifiReceiver()
        bottomNavigationMenu = findViewById(R.id.bottomNavigationView)
        
        setUpInternetObserver()

        bottomNavigationMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.duolingoClassFragment ->{
                    fragment = ClassroomFragment.newInstance()
                    setFragement(fragment)
                }

                R.id.duolingoProfile->{
                    fragment = ProfileFragment.newInstance()
                    setFragement(fragment)
                }
/*
                R.id.duolingoStore->{
                    fragment = StoreFragment.newInstance()
                    setFragement(fragment)
                }


 */
                R.id.duolingoHighScore->{
                    fragment = HighScoreFragment.newInstance()
                    setFragement(fragment)
                }
            }
            true
        }
        

    }

    fun setUpInternetObserver(){
        // TODO: 4/18/2021 in the future we will need to see if the user is saved
        Util.hasInternet.observe(this, Observer {hasInternet ->
            if (hasInternet){
                if(viewModel.currentFragment==null)
                viewModel.currentFragment = ClassroomFragment.newInstance()
                setFragement(viewModel.currentFragment!!)
            }
            if (!hasInternet&&Util.useAppWithInternet){
                fragment = OfflineFragment.newInstance()
                setFragement(fragment)
            }
        })

        viewModel.lessonInProgress.observe(this, Observer {
            if(it){
                bottomNavigationMenu.visibility = View.GONE
            }else{
                bottomNavigationMenu.visibility = View.VISIBLE
            }
        })

        viewModel.level.observe(this, Observer {
            when(it){
                2->{
                    val toast = layoutInflater.inflate(R.layout.toast_stage_1,null)
                    toast.apply {
                        var text = findViewById<TextView>(R.id.awardTV)
                        text.text = "Beginners Pride!"

                    }
                    makeToast(toast)
                }
                3->{
                    val toast = layoutInflater.inflate(R.layout.toast_stage_1,null)
                    toast.apply {
                        var text = findViewById<TextView>(R.id.awardTV)
                        text.text = "Translation Star!"
                    }
                    makeToast(toast)
                }
                4->{
                    val toast = layoutInflater.inflate(R.layout.toast_stage_1,null)
                    toast.apply {
                        var text = findViewById<TextView>(R.id.awardTV)
                        text.text = "Language Master!"
                    }
                    makeToast(toast)
                }
            }
        })
    }

    fun makeToast(view: View){
        val costomToast = Toast.makeText(this@MainActivity,"Toast:Gravity.Top",Toast.LENGTH_LONG)
        costomToast.setGravity(Gravity.CENTER,0,0)
        costomToast.view = view
        costomToast.show()
    }

    fun setFragement(fragment:Fragment){

            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainActivityFragmentContainer, fragment)
                    .commit()

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

    override fun onLessonSelected(lesson: Lesson,lessonNumber:Int) {
        var fragment = LessonFragment.newInstance(lesson,lessonNumber)
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFragmentContainer, fragment)
            .addToBackStack("lesson")
            .commit()
    }

    fun showExitDialog(){
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.exit_app_dialog, null)
        val yesButton = dialogView.findViewById(R.id.yesButton) as Button
        val noButton = dialogView.findViewById(R.id.noButton) as Button

        val alertDialog = AlertDialog.Builder(this@MainActivity)
        alertDialog.setView(dialogView).setCancelable(false)


        val dialog = alertDialog.create()
        dialog.show()

        yesButton.setOnClickListener {
            dialog.dismiss()
            this.finish()
        }

        noButton.setOnClickListener {
            dialog.dismiss()
        }
    }


    override fun onBackPressed() {
        if(!viewModel.lessonInProgress.value!!){
            showExitDialog()
        }else{
            val inflater = layoutInflater
            val dialogView = inflater.inflate(R.layout.back_pressed_alert_dialog, null)
            val yesButton = dialogView.findViewById(R.id.continueButton) as Button
            val noButton = dialogView.findViewById(R.id.dismissButton) as Button

            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setView(dialogView).setCancelable(false)


            val dialog = alertDialog.create()
            dialog.show()

            yesButton.setOnClickListener {
                viewModel.xpPointsNow = 0
                viewModel.lessonInProgress.postValue(false)
                var fm = supportFragmentManager
                fm.popBackStack()
                dialog.dismiss()

            }

            noButton.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

}