package com.jacoblip.andriod.duolingo.Views

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jacoblip.andriod.duolingo.Data.models.*
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.Data.services.SoundPlayer
import com.jacoblip.andriod.duolingo.R


class LessonFragment(lesson: Lesson,lessonNumber:Int):Fragment() {
    val lessonNumber = lessonNumber
    var arrayOfUserAnswers:ArrayList<Boolean> = arrayListOf()
    val lesson = lesson
    lateinit var myView:View
    val lessonQuestions = lesson.quetions.toMutableList()
    var num = 0
    var numberOfCorrectAnswers = 0
    var numberQuestion :MutableLiveData<Int> = MutableLiveData()
    lateinit var user: User
    lateinit var viewModel: MainViewModel
    lateinit var numberOfHearts:TextView
    lateinit var backToLessonsButton:ImageButton
    lateinit var progressBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.lessonInProgress.postValue(true)
        val view = inflater.inflate(R.layout.fragment_lesson,container,false )
        view.apply {
            progressBar = findViewById(R.id.questionProgressBar)
            numberOfHearts = findViewById(R.id.numOfHearts)
            backToLessonsButton = findViewById(R.id.backToLessonsButton)
            numberOfHearts.text = "3"
            backToLessonsButton.setOnClickListener {
                showDialog(view,1)
            }
            lessonQuestions.shuffle()
        }
        myView = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObservers()
        numberQuestion.postValue(num)
        ObjectAnimator.ofInt(progressBar, "progress", 12*(num+1)).setDuration(500).start()
    }

    fun showDialog(view: View,type:Int){
        when(type) {
            1-> {
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.back_pressed_alert_dialog, null)
                val yesButton = dialogView.findViewById(R.id.continueButton) as Button
                val noButton = dialogView.findViewById(R.id.dismissButton) as Button

                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setView(dialogView).setCancelable(false)


                val dialog = alertDialog.create()
                dialog.show()

                yesButton.setOnClickListener {
                    viewModel.xpPointsNow = 0
                  finishLesson()
                    dialog.dismiss()

                }

                noButton.setOnClickListener {
                    dialog.dismiss()
                }
            }
            2 ->{
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.out_of_life_dialog, null)
                val okButton = dialogView.findViewById(R.id.okButton) as Button

                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setView(dialogView).setCancelable(false)
                val dialog = alertDialog.create()
                dialog.show()
                SoundPlayer.playSound(requireContext(),3)

                okButton.setOnClickListener {
                    viewModel.xpPointsNow = 0
                    finishLesson()
                    dialog.dismiss()
                }
            }
            3->{
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.lesson_complete_dialog, null)
                val okButton = dialogView.findViewById(R.id.lessonCompleteContinueButton) as Button

                val alertDialog = AlertDialog.Builder(requireContext())
                alertDialog.setView(dialogView).setCancelable(false)
                val dialog = alertDialog.create()
                dialog.show()
                SoundPlayer.playSound(requireContext(),4)

                okButton.setOnClickListener {
                    var xpForLessonCompletion = 2*numberOfCorrectAnswers
                    viewModel.xpPointsNow+=xpForLessonCompletion
                    viewModel.upDateLesson(lessonNumber)
                    dialog.dismiss()
                    finishLesson()
                }
            }
        }
    }

    fun finishLesson(){
        viewModel.lessonInProgress.postValue(false)
        var fm = (activity as AppCompatActivity).supportFragmentManager
        fm.popBackStack()
    }

    fun setUpObservers(){
        numberQuestion.observe(viewLifecycleOwner, Observer {
            getQuestion(it)
        })
        viewModel.user.observe(viewLifecycleOwner, Observer {
            user = it
        })
    }

    fun getQuestion(num:Int){

        if(num==lesson.quetions.size) {
            showDialog(myView,3)
            return
        }

        var question =lessonQuestions[num]
        when(question.type){
            QuestionType.TranslationQ->{
                val fragment = QuestionSentenceFragment.newInstance(lesson, question)
                setFragement(fragment)
            }
            QuestionType.PictureQ->{
                val fragment = QuestionPictureFragment.newInstance(lesson, question )
                setFragement(fragment)
            }
            QuestionType.HearingQ->{
                val fragment = QuestionHearingFragment.newInstance(lesson, question )
                setFragement(fragment)
            }
            QuestionType.TwinsQ->{
                val fragment = QuestionTwinsFragment.newInstance(lesson, question)
                setFragement(fragment)
            }
            QuestionType.FreeStyleQ->{
                val fragment = QuestionFreeStyleFragment.newInstance(lesson, question)
                setFragement(fragment)
            }
        }
    }

    fun setFragement(fragment:Fragment){
        childFragmentManager
                .beginTransaction()
                .replace(R.id.lessonFragmentContainer, fragment)
                .commit()
    }




    fun handleAnswer(answerIsCorrect: Boolean){

        arrayOfUserAnswers.add(answerIsCorrect)
        var numOfHearts = numberOfHearts.text.toString().toInt()
        if (numOfHearts==0){
            view?.let { showDialog(it,2) }
            return
        }
        if(!answerIsCorrect){
           numberOfHearts.text =  (numOfHearts-1).toString()
        }

        if(answerIsCorrect) {
            numberOfCorrectAnswers++
            viewModel.xpPointsNow+=5
        }

        num++
        ObjectAnimator.ofInt(progressBar, "progress", 12*(num+1)).setDuration(500).start()
        numberQuestion.postValue(num)
    }


    companion object{
        fun newInstance(lesson: Lesson,lessonNumber: Int):LessonFragment{
            return LessonFragment(lesson,lessonNumber)
        }
    }
}