package com.jacoblip.andriod.duolingo.Views

import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.models.Question
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.Data.services.SoundPlayer
import com.jacoblip.andriod.duolingo.R
import java.util.*


class QuestionFreeStyleFragment(lesson: Lesson,question: Question):Fragment(),TextToSpeech.OnInitListener {

    lateinit var parentFragment:LessonFragment
    var checkButtonPressed = true
    var isCorrectAnswer = true
    val lesson = lesson
    val question = question
    lateinit var viewModel: MainViewModel
    lateinit var tts: TextToSpeech
    lateinit var speakSentenceButton: ImageButton
    lateinit var lessonName: TextView
    lateinit var englishSentence: TextView
    lateinit var correctAnswer:TextView
    lateinit var answerResultTV:TextView
    lateinit var resultLayout: ConstraintLayout
    lateinit var checkAnswerButton: Button
    lateinit var multiLineInput: EditText
    var fragmentReady: MutableLiveData<Boolean> = MutableLiveData()
    var editTextNotEmpty : MutableLiveData<Boolean> = MutableLiveData()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_question_free_style,container,false)
        tts = TextToSpeech(context,this)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentFragment = getParentFragment() as LessonFragment
        setUpViews(view)
        setUpObserver()
        view.apply {
            lessonName.text = lesson.name
            checkAnswerButton.setOnClickListener {
                checkAnswer()
            }
            setUpQuestion()
        }
        fragmentReady.postValue(true)
    }

    fun setUpViews(view: View){
        view.apply {
            lessonName = findViewById(R.id.fragmentLessonLessonNameFreeStyle)
            speakSentenceButton = findViewById(R.id.speakSentenceButtonFreeStyle)
            englishSentence = findViewById(R.id.englishSentenceFreeStyle)
            correctAnswer = findViewById(R.id.correctAnswerFreeStyle)
            answerResultTV = findViewById(R.id.answerResultTVFreeStyle)
            resultLayout = findViewById(R.id.resultLayoutFreeStyle)
            multiLineInput = findViewById(R.id.editTextTextMultiLineFreeStyle)
            checkAnswerButton = findViewById(R.id.checkAnswerButtonFreeStyle)
        }

       multiLineInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
               if(multiLineInput.text.toString()==""){
                   setCheckButtonEnabled(false)
               }else{
                   setCheckButtonEnabled(true)
               }
            }
        })
    }
    fun setUpObserver(){
        editTextNotEmpty.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                setCheckButtonEnabled(it)
        })
    }

    fun setUpQuestion(){
        englishSentence.text = question.englishSentence
        speakSentenceButton.setOnClickListener {
            speakOut(question.englishSentence)
        }
    }


    fun checkAnswer(){
        if(!checkButtonPressed) {
            var str = multiLineInput.text.toString().trim()
            var strArr = str.split(' ')
            var myAnswer = ""
            for(i in strArr.indices){
                myAnswer+=strArr[i]+" "
            }
            myAnswer.trim()

            var answer = question.hebrewSentence
            answerResultTV.visibility = View.VISIBLE
            if (myAnswer == answer) {

                answerResultTV.setText(R.string.answer_is_correct)
                resultLayout.setBackgroundColor(Color.GREEN)
                isCorrectAnswer = true
                SoundPlayer.playSound(requireContext(), 1)
            }else{
                answerResultTV.setText(R.string.answer_is_worg)
                resultLayout.setBackgroundColor(Color.RED)
                correctAnswer.visibility = View.VISIBLE
                correctAnswer.text = "Answer: ${question.hebrewSentence}"
                SoundPlayer.playSound(requireContext(),2)
            }
            checkAnswerButton.text = "CONTINUE"
            checkButtonPressed = true
        }else{
                parentFragment.handleAnswer(isCorrectAnswer)
        }

    }

    fun setCheckButtonEnabled(boolean: Boolean){
        checkAnswerButton.isEnabled = boolean
    }



    override fun onInit(status: Int) {

        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language specified is not supported!")
            } else {
                speakSentenceButton!!.isEnabled = true
            }

        } else {
            Log.e("TTS", "Initilization Failed!")
        }

    }

    private fun speakOut(string: String) {
        val text = string
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onDestroy() {
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    companion object{
        fun newInstance(lesson: Lesson, question: Question):QuestionFreeStyleFragment{
            return QuestionFreeStyleFragment(lesson,question)
        }
    }
}