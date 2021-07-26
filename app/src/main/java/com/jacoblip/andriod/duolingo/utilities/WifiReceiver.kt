package com.jacoblip.andriod.duolingo.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi


const val TAG  = "WifiReceiver"
class WifiReceiver() : BroadcastReceiver() {
    // TODO: 4/11/2021 live data from reciver to fragment
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onReceive(context: Context, intent: Intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent!!.action) {
            val noConnectivity: Boolean = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            if (noConnectivity) {
                Log.i(TAG,"Not Connected")
                Util.hasInternet.postValue(false)

            } else {
                Log.i(TAG,"Connected")
                Util.hasInternet.postValue(true)
            }
        }
    }
}