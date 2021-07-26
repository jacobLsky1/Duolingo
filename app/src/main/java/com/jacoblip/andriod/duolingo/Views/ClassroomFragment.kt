package com.jacoblip.andriod.duolingo.Views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.models.User
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.R
import com.jacoblip.andriod.duolingo.Views.adapters.LessonItemAdapter

class ClassroomFragment:Fragment() {

    var appUser: User? = null
    lateinit var lessonRV:RecyclerView
    var appLessons:Array<Lesson>? = null
    var dataReady:MutableLiveData<Boolean> = MutableLiveData(false)
    lateinit var viewModel: MainViewModel
    lateinit var progressBar:ProgressBar

    interface Callbacks {
        fun onLessonSelected(lesson:Lesson,lessonNumber:Int)
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
        val view = inflater.inflate(R.layout.fragment_classroom,container,false )
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.currentFragment = ClassroomFragment.newInstance()
        view.apply{
            lessonRV = view.findViewById(R.id.lessonRV)
            progressBar = view.findViewById(R.id.progressBar2)
            lessonRV.layoutManager = GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL,false)
        }
        setUpObservers()
        return view
    }

    fun setUpObservers(){
        viewModel.user.observe(viewLifecycleOwner, Observer {user->
           appUser = user
            isDataReady()
        })

        viewModel.lessons.observe(viewLifecycleOwner, Observer {lessons ->
            appLessons = lessons
            isDataReady()
        })

        dataReady.observe(viewLifecycleOwner, Observer {
            if(it){
                setUpAdapter()
            }
        })

    }

    fun isDataReady(){
        if(appUser!=null&&appLessons!=null) {
            dataReady.postValue(true)
        }
    }
    fun setUpAdapter(){
            lessonRV.adapter = callbacks?.let {
                LessonItemAdapter(appLessons!!, it, appUser!!)
            }
            progressBar.visibility = View.GONE
    }

    companion object{
        fun newInstance():ClassroomFragment{
            return ClassroomFragment()
        }
    }
}