package com.jacoblip.andriod.duolingo.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jacoblip.andriod.duolingo.R

class OfflineFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_no_internet,container,false)
        view.apply {

        }
        return view
    }


    companion object{
        fun newInstance():OfflineFragment {
            return OfflineFragment()
        }
    }
}