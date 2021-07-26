package com.jacoblip.andriod.duolingo.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.R
import com.jacoblip.andriod.duolingo.Views.adapters.UserItemAdapter

class HighScoreFragment:Fragment() {

    lateinit var UserRV: RecyclerView
    lateinit var viewModel: MainViewModel
    lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.currentFragment = HighScoreFragment.newInstance()
        val view = inflater.inflate(R.layout.fragment_high_score,container,false)
        view.apply {
            UserRV = view.findViewById(R.id.highScoreRV)
            progressBar = view.findViewById(R.id.progressBar)
            UserRV.layoutManager = LinearLayoutManager(context)
            setUpObservers()
            viewModel.getUsers()
        }
        return view
    }

    fun setUpObservers(){
        viewModel.allUsers.observe(viewLifecycleOwner, Observer {
            it.let {
                UserRV.adapter = UserItemAdapter(it)
                progressBar.visibility = View.GONE
            }
        })
    }

    companion object{
        fun newInstance():HighScoreFragment{
            return HighScoreFragment()
        }
    }
}