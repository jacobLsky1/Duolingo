package com.jacoblip.andriod.duolingo.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.database.FirebaseDatabase
import com.jacoblip.andriod.duolingo.R

class GreetingsFragment(context: Context):Fragment() {

    var mycontext = context
    lateinit var haveAccountButton:Button
    lateinit var getStartedButton:Button

    interface Callbacks {
        fun onButtonSelected(fragment: Fragment)
    }

    private var callbacks: Callbacks? = null

    //the callback functions
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_greetings_doulingo,container,false)
        haveAccountButton = view.findViewById(R.id.haveAccountButton)
        getStartedButton = view.findViewById(R.id.getStartedButton)

        getStartedButton.setOnClickListener {
            callbacks?.onButtonSelected(SignUpFragment.newInstance(mycontext))
        }

        haveAccountButton.setOnClickListener {
            callbacks?.onButtonSelected(LoginFragment.newInstance())
        }
        return view
    }



    companion object{
        fun newInstance(context: Context): GreetingsFragment {
            return GreetingsFragment(context)
        }
    }
}