package com.jacoblip.andriod.duolingo.Views

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jacoblip.andriod.duolingo.Data.models.ButtonPosition
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.models.Question
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import com.jacoblip.andriod.duolingo.Data.services.SoundPlayer
import com.jacoblip.andriod.duolingo.R
import com.jacoblip.andriod.duolingo.utilities.Util
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class QuestionHearingFragment(lesson: Lesson,question: Question):Fragment(), TextToSpeech.OnInitListener {

    lateinit var parentFragment:LessonFragment
    val lesson = lesson
    val question = question
    var checkButtonPressed = false
    var userAnswer:ArrayList<String> = arrayListOf()
    var isCorrectAnswer = false
    var firstLineFull = false
    lateinit var viewModel: MainViewModel
    lateinit var tts: TextToSpeech
    lateinit var lessonName: TextView
    lateinit var line1: ImageView
    lateinit var line2: ImageView
    lateinit var speakSentenceButton: ImageButton
    lateinit var word1TV: TextView
    lateinit var word2TV: TextView
    lateinit var word3TV: TextView
    lateinit var word4TV: TextView
    lateinit var word5TV: TextView
    lateinit var word6TV: TextView
    lateinit var plcHLine1: TextView
    lateinit var plcHLine2: TextView
    lateinit var answerResultTV:TextView
    lateinit var correctAnswer:TextView
    lateinit var resultLayout: ConstraintLayout
    lateinit var checkAnswerButton:Button
    lateinit var wordTVS:Array<TextView>
    var wordx:Float? = null
    var wordy:Float? = null
    lateinit var buttonOriganalPositions:MutableList<ButtonPosition>
    lateinit var placeHolderPositions:MutableList<ButtonPosition>
    var fragmentReady: MutableLiveData<Boolean> = MutableLiveData()
    var buttonsMoved:ArrayList<TextView> = arrayListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_question_hearing,container,false)
        tts = TextToSpeech(context,this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentFragment = getParentFragment() as LessonFragment
        setUpViews(view)
        setUpObserver()
        view.apply {
            checkAnswerButton.setOnClickListener {
                checkAnswer()
            }
            setUpQuestion()
        }
        setUpClickEvents()
        fragmentReady.postValue(true)
    }

    fun setUpObserver(){
        fragmentReady.observe(viewLifecycleOwner, Observer {
            if(it){
                getPositions()
                speakOut(question.englishSentence)
            }
        })
    }

    fun setUpViews(view: View){
        view.apply {
            lessonName = findViewById(R.id.fragmentLessonLessonName)
            speakSentenceButton = findViewById(R.id.speakSentenceButton)
            word1TV  = findViewById(R.id.word1)
            word2TV  = findViewById(R.id.word2)
            word3TV  = findViewById(R.id.word3)
            word4TV  = findViewById(R.id.word4)
            word5TV  = findViewById(R.id.word5)
            word6TV  = findViewById(R.id.word6)
            plcHLine1 = findViewById(R.id.plcH1)
            plcHLine2 = findViewById(R.id.plcH2)
            answerResultTV = findViewById(R.id.answerResultTV)
            correctAnswer = findViewById(R.id.correctAnswer)
            resultLayout = findViewById(R.id.resusltLayout)
            line1 = findViewById(R.id.Line1)
            line2 = findViewById(R.id.Line2)
            checkAnswerButton = findViewById(R.id.checkAnswerButton)
            checkAnswerButton.isEnabled = false
            wordTVS= arrayOf(word1TV,word2TV,word3TV,word4TV,word5TV,word6TV)

            speakSentenceButton.setOnClickListener {
                speakOut(question.englishSentence)
            }

        }
    }

    fun setCheckButtonEnabled(boolean: Boolean){
            checkAnswerButton.isEnabled = boolean
    }

    fun getPositions(){
        buttonOriganalPositions = mutableListOf()
        placeHolderPositions = mutableListOf()
        for (word in wordTVS)
            buttonOriganalPositions.add(ButtonPosition(word.x, word.y))
    }

    fun checkAnswer(){
        if(!checkButtonPressed) {
            var answer = question.hebrewSentence
            var str = ""

            for (word in userAnswer)
                str += "$word "

            if (str.isNotEmpty())
                str = str.substring(0, str.lastIndex)

            answerResultTV.visibility = View.VISIBLE

            if (str == answer) {

                answerResultTV.setText(R.string.answer_is_correct)
                resultLayout.setBackgroundColor(Color.GREEN)
                isCorrectAnswer = true
                SoundPlayer.playSound(requireContext(),1)
            }else{
                answerResultTV.setText(R.string.answer_is_worg)
                resultLayout.setBackgroundColor(Color.RED)
                correctAnswer.visibility = View.VISIBLE
                correctAnswer.text = "Answer: ${question.hebrewSentence}"
                SoundPlayer.playSound(requireContext(),2)
            }
            checkAnswerButton.text = "CONTINUE"
            checkButtonPressed = true
        }else {
            parentFragment.handleAnswer(isCorrectAnswer)
        }
    }

    fun setUpClickEvents(){
        word1TV.setOnClickListener {
            if(!checkButtonPressed) {
                if (buttonsMoved.contains(word1TV)) {
                    closeGaps(buttonsMoved.indexOf(word1TV))
                    word1TV.animate().x(buttonOriganalPositions[0].x).y(buttonOriganalPositions[0].y).setDuration(200).start()

                } else {
                    userAnswer.add(word1TV.text.toString())
                    moveWord(word1TV)
                }
                Util.logSentence(userAnswer)
            }
        }
        word2TV.setOnClickListener {
            if(!checkButtonPressed) {
                if (buttonsMoved.contains(word2TV)) {
                    closeGaps(buttonsMoved.indexOf(word2TV))
                    word2TV.animate().x(buttonOriganalPositions[1].x).y(buttonOriganalPositions[1].y).setDuration(200).start()

                } else {
                    userAnswer.add(word2TV.text.toString())
                    moveWord(word2TV)
                }
                Util.logSentence(userAnswer)
            }
        }
        word3TV.setOnClickListener {
            if(!checkButtonPressed) {
                if (buttonsMoved.contains(word3TV)) {
                    closeGaps(buttonsMoved.indexOf(word3TV))
                    word3TV.animate().x(buttonOriganalPositions[2].x).y(buttonOriganalPositions[2].y).setDuration(200).start()
                } else {
                    userAnswer.add(word3TV.text.toString())
                    moveWord(word3TV)
                }
                Util.logSentence(userAnswer)
            }
            }
            word4TV.setOnClickListener {
                if(!checkButtonPressed) {
                    if (buttonsMoved.contains(word4TV)) {
                        closeGaps(buttonsMoved.indexOf(word4TV))
                        word4TV.animate().x(buttonOriganalPositions[3].x).y(buttonOriganalPositions[3].y).setDuration(200).start()
                    } else {
                        userAnswer.add(word4TV.text.toString())
                        moveWord(word4TV)
                    }
                    Util.logSentence(userAnswer)
                }
            }
            word5TV.setOnClickListener {
                if(!checkButtonPressed) {
                    if (buttonsMoved.contains(word5TV)) {
                        closeGaps(buttonsMoved.indexOf(word5TV))
                        word5TV.animate().x(buttonOriganalPositions[4].x).y(buttonOriganalPositions[4].y).setDuration(200).start()
                    } else {
                        userAnswer.add(word5TV.text.toString())
                        moveWord(word5TV)
                    }
                    Util.logSentence(userAnswer)
                }
            }
            word6TV.setOnClickListener {
                if(!checkButtonPressed) {
                    if (buttonsMoved.contains(word6TV)) {
                        closeGaps(buttonsMoved.indexOf(word6TV))
                        word6TV.animate().x(buttonOriganalPositions[5].x).y(buttonOriganalPositions[5].y).setDuration(200).start()
                    } else {
                        userAnswer.add(word6TV.text.toString())
                        moveWord(word6TV)
                    }
                    Util.logSentence(userAnswer)

                }
           }
        }

    fun setUpQuestion(){
        var hebrewSentence = question.hebrewSentence
        var hebrewSentenceWords = hebrewSentence.split(" ").toMutableList()

        while(hebrewSentenceWords.size<6){
            var num = Random.nextInt(0,20)
            var word = Util.spareWords[num]
            if(hebrewSentenceWords.contains(word)) {
                continue
            } else {
                hebrewSentenceWords.add(word)
            }
        }
        hebrewSentenceWords.shuffle()
        for(i in 0..5){
            wordTVS[i].text = hebrewSentenceWords[i]
        }

        getPositions()
        speakOut(question.englishSentence)
    }



    fun moveWord(word:TextView){
        setCheckButtonEnabled(true)
        if(buttonsMoved.isEmpty()){
            word.animate().x(plcHLine1.x).y(plcHLine1.y).setDuration(200).start()
            buttonsMoved.add(word)
            return
        }else{
            val beforeWord = buttonsMoved.last()

            if(beforeWord.x-word.width<30){
                firstLineFull = true
                word.animate().x(plcHLine2.x).y(plcHLine2.y).setDuration(200).start()
                buttonsMoved.add(word)
                return
            }

            word.animate().x((beforeWord.x-word.width)-20F).y(beforeWord.y).setDuration(200).start()
            buttonsMoved.add(word)
            return

        }

    }

    fun closeGaps(numberToBeRemoved:Int){
        userAnswer.remove(userAnswer[numberToBeRemoved])
        closeTheGaps(numberToBeRemoved)
        wordy = null
        wordx = null
        if(buttonsMoved.isEmpty())
            setCheckButtonEnabled(false)
    }
    fun closeTheGaps(numberToBeRemoved: Int){
        val animationList: MutableList<ViewPropertyAnimator> = mutableListOf()
        wordx = buttonsMoved[numberToBeRemoved].x +buttonsMoved[numberToBeRemoved].width
        wordy = buttonsMoved[numberToBeRemoved].y

        val listOfXforWords:ArrayList<Float> = arrayListOf()
        for(i in numberToBeRemoved+1..buttonsMoved.lastIndex){
            var x = wordx!!-buttonsMoved[i].width
            listOfXforWords.add(x!!)
            x-=20F
            wordx =x
        }
        var j = 0
        for(i in numberToBeRemoved+1..buttonsMoved.lastIndex) {
            wordy = if (i < buttonsMoved.lastIndex && (listOfXforWords[j] - buttonsMoved[i + 1].width < 30)) {
                plcHLine2.y
            } else {
                plcHLine1.y
            }
            var animationForView = buttonsMoved[i].animate().x(listOfXforWords[j]).y(wordy!!).setDuration(200)
            animationList.add(animationForView)
            j++
        }

        buttonsMoved.remove(buttonsMoved[numberToBeRemoved])
        for(animation in animationList)
            animation.start()
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
        fun newInstance(lesson: Lesson,question: Question):QuestionHearingFragment{
            return QuestionHearingFragment(lesson,question)
        }
    }
}