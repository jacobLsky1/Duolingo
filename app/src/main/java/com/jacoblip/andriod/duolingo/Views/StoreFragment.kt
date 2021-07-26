package com.jacoblip.andriod.duolingo.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.R

class StoreFragment:Fragment() {

    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.currentFragment = StoreFragment.newInstance()
        val view = inflater.inflate(R.layout.fragment_store,container,false )
        view.apply{

        }
        return view
    }

    companion object{
        fun newInstance():StoreFragment{
            return StoreFragment()
        }
    }
}