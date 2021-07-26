package com.jacoblip.andriod.duolingo.Views

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.models.Picture
import com.jacoblip.andriod.duolingo.Data.models.Question
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.Data.services.SoundPlayer
import com.jacoblip.andriod.duolingo.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random


// TODO: 5/4/2021 DO IT!!!!!
class QuestionPictureFragment(lesson: Lesson, question: Question):Fragment() {

    var lesson = lesson
    var question = question
    var checkButtonPressed = false
    var photos:Array<Picture> = arrayOf()
    lateinit var checkAnswerButton: Button
    lateinit var parentFragment:LessonFragment
    lateinit var viewModel: MainViewModel
    lateinit var frame1:CardView
    lateinit var frame2:CardView
    lateinit var frame3:CardView
    lateinit var frame4:CardView
    lateinit var pic1:ImageView
    lateinit var pic2:ImageView
    lateinit var pic3:ImageView
    lateinit var pic4:ImageView
    lateinit var answerResultTV:TextView
    lateinit var resultLayout: ConstraintLayout
    lateinit var wordTV:TextView
    var correctAnswer = ""
    var userAnswer = ""
    var picArray : ArrayList<Picture> = arrayListOf()
    var isCorrectAnswer = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_question_picture,container,false)
        setUpViews(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentFragment = getParentFragment() as LessonFragment
        setUpViews(view)
        photos = viewModel.photos
        view.apply {
            checkAnswerButton.setOnClickListener {
                checkAnswer()
            }
            setUpQuestion()
        }

        setUpClickEvents()
    }


    fun setUpViews(view: View){
        view.apply {
            checkAnswerButton = findViewById(R.id.checkAnswerButton)
            checkAnswerButton.isEnabled = false
            frame1 = findViewById(R.id.card1)
            frame2 = findViewById(R.id.card2)
            frame3 = findViewById(R.id.card3)
            frame4 = findViewById(R.id.card4)
            pic1 = findViewById(R.id.picture1)
            pic2 = findViewById(R.id.picture2)
            pic3 = findViewById(R.id.picture3)
            pic4 = findViewById(R.id.picture4)
            answerResultTV = findViewById(R.id.answerResultTV)
            resultLayout = findViewById(R.id.resultLayout)
            wordTV = findViewById(R.id.hebrewWord)
        }
    }

    fun setCheckButtonEnabled(boolean: Boolean){
        checkAnswerButton.isEnabled = boolean
    }

    fun checkAnswer(){
        if(!checkButtonPressed) {
            answerResultTV.visibility = View.VISIBLE
            if (userAnswer == correctAnswer) {

                answerResultTV.setText(R.string.answer_is_correct)
                resultLayout.setBackgroundColor(Color.GREEN)
                isCorrectAnswer = true
                SoundPlayer.playSound(requireContext(),1)
            }else{
                answerResultTV.setText(R.string.answer_is_worg)
                resultLayout.setBackgroundColor(Color.RED)
                SoundPlayer.playSound(requireContext(),2)
            }
            checkAnswerButton.text = "CONTINUE"
            checkButtonPressed = true
        }else{
            parentFragment.handleAnswer(isCorrectAnswer)
        }
    }

    fun resetColors(){
        var frames = arrayOf(frame1,frame2,frame3,frame4)
        for (frame in frames){
            frame.setCardBackgroundColor(Color.WHITE)
        }
    }

    fun setUpClickEvents(){
        pic1.setOnClickListener {
            if(!checkButtonPressed) {
                var picturePressed = picArray[0]
                if (userAnswer == picturePressed.url) {
                    frame1.setCardBackgroundColor(Color.WHITE)
                    userAnswer = ""
                    setCheckButtonEnabled(false)
                } else {
                    resetColors()
                    frame1.setCardBackgroundColor(Color.CYAN)
                    userAnswer = picturePressed.url
                    setCheckButtonEnabled(true)
                }
            }

        }
        pic2.setOnClickListener {
            if(!checkButtonPressed) {
                var picturePressed = picArray[1]
                if (userAnswer == picturePressed.url) {
                    frame2.setCardBackgroundColor(Color.WHITE)
                    userAnswer = ""
                    setCheckButtonEnabled(false)
                } else {
                    resetColors()
                    frame2.setCardBackgroundColor(Color.CYAN)
                    userAnswer = picturePressed.url
                    setCheckButtonEnabled(true)
                }
            }
        }
        pic3.setOnClickListener {
            if(!checkButtonPressed) {
                var picturePressed = picArray[2]
                if (userAnswer == picturePressed.url) {
                    frame3.setCardBackgroundColor(Color.WHITE)
                    userAnswer = ""
                    setCheckButtonEnabled(false)
                } else {
                    resetColors()
                    frame3.setCardBackgroundColor(Color.CYAN)
                    userAnswer = picturePressed.url
                    setCheckButtonEnabled(true)
                }
            }
        }
        pic4.setOnClickListener {
            if (!checkButtonPressed) {
                var picturePressed = picArray[3]
                if (userAnswer == picturePressed.url) {
                    frame4.setCardBackgroundColor(Color.WHITE)
                    userAnswer = ""
                    setCheckButtonEnabled(false)
                } else {
                    resetColors()
                    frame4.setCardBackgroundColor(Color.CYAN)
                    userAnswer = picturePressed.url
                    setCheckButtonEnabled(true)
                }
            }
        }
    }

    fun setUpQuestion(){
        wordTV.text = question.picWord
        var correctPicture = photos[lesson.lessonNumber-1]
        correctAnswer = correctPicture.url
        picArray.add(correctPicture)
       while(picArray.size<4){
            var num = Random.nextInt(23)
            var pic = photos[num]
            if(picArray.contains(pic))
                continue

            picArray.add(pic)
        }
        picArray.shuffle()

        var pics = arrayOf(pic1,pic2,pic3,pic4)
        for(i in 0..3){
           Glide.with(this).load(picArray[i].url).into(pics[i])
        }
    }



    companion object{
        fun newInstance(lesson: Lesson,question: Question):QuestionPictureFragment{
            return QuestionPictureFragment(lesson,question)
        }
    }
}