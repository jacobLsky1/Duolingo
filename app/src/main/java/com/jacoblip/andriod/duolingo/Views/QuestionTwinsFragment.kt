package com.jacoblip.andriod.duolingo.Views

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.N)
class QuestionTwinsFragment(lesson: Lesson,question: Question):Fragment() {

    lateinit var parentFragment:LessonFragment
    var checkButtonPressed = false
    var isCorrectAnswer = false
    val lesson = lesson
    val question = question
    var amountOfPlacesFilled = 0
    var numbersFiledLD : MutableLiveData<Int> = MutableLiveData(0)
    lateinit var viewModel: MainViewModel
    lateinit var lessonName: TextView
    lateinit var word1TV:TextView
    lateinit var word2TV:TextView
    lateinit var word3TV:TextView
    lateinit var word4TV:TextView
    lateinit var word5TV:TextView
    lateinit var word6TV:TextView
    lateinit var twin1:TextView
    lateinit var twin2:TextView
    lateinit var twin3:TextView
    lateinit var twinA:TextView
    lateinit var twinB:TextView
    lateinit var twinC:TextView
    lateinit var answerResultTV:TextView
    lateinit var resultLayout: ConstraintLayout
    lateinit var checkAnswerButton: Button
    lateinit var wordTVS:ArrayList<TextView>
    lateinit var placeHolderTVS:ArrayList<TextView>
    var hebrewWords = arrayOf(question.twinWords!![0],question.twinWords!![1],question.twinWords!![2])
    var englishWords = arrayOf(question.twinWords!![3],question.twinWords!![4],question.twinWords!![5])
    var allWords = arrayOf(hebrewWords[0],hebrewWords[1],hebrewWords[2],englishWords[0],englishWords[1],englishWords[2])
    var placesFilled:ArrayList<TextView?> = arrayListOf(null,null,null,null,null,null)
    lateinit var hebrewPlaceHolders:Array<TextView>
    lateinit var englishPlaceHolders:Array<TextView>
    var answer1 = hebrewWords[0]+englishWords[0]
    var answer2 = hebrewWords[1]+englishWords[1]
    var answer3 = hebrewWords[2]+englishWords[2]
    var answers = arrayListOf(answer1,answer2,answer3)
    var fragmentReady: MutableLiveData<Boolean> = MutableLiveData()
    var buttonOriganalPositions:MutableList<ButtonPosition> = mutableListOf()
    var placeHolderPositions:MutableList<ButtonPosition> = mutableListOf()

    val dragListener = View.OnDragListener{ view:View, event ->
        when(event.action){
            DragEvent.ACTION_DRAG_STARTED->{
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED->{
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION->true
            DragEvent.ACTION_DRAG_EXITED->{
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP->{
                val item = event.localState as TextView
                val word = item.text.toString()
                var destnation = view as TextView
                var textViewNumber = placeHolderTVS.indexOf(destnation)
                if((englishWords.contains(word)&&englishPlaceHolders.contains(destnation))||(hebrewWords.contains(word)&&hebrewPlaceHolders.contains(destnation))&& placesFilled[textViewNumber]==null) {
                    destnation.text = word
                    destnation.background = resources.getDrawable(R.drawable.button_shape_passed)
                    placesFilled[textViewNumber] = item
                    Log.i("drag","dragged")
                    upDateNumbersFilled(true)
                }else{
                    val item = event.localState as TextView
                    item.visibility = View.VISIBLE
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED-> {
                view.invalidate()
                when(event.result) {
                    true ->{
                        //showSpots()
                    }
                    else ->{
                        // drop didn't work
                        val item = event.localState as TextView
                        item.visibility = View.VISIBLE
                    }
                }

                // returns true; the value is ignored.
                true
            }
            else -> false
        }
    }

    fun upDateNumbersFilled(add:Boolean){
        if(add){
            if(amountOfPlacesFilled<6)
            amountOfPlacesFilled++
        }
        else{
            if(amountOfPlacesFilled>0)
                amountOfPlacesFilled--
        }
        numbersFiledLD.postValue(amountOfPlacesFilled)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_question_twins,container,false)
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
        setUpClickEvents()
        fragmentReady.postValue(true)


    }

    fun setUpViews(view: View){
        view.apply {
            lessonName = findViewById(R.id.fragmentLessonLessonNameTwins)
            word1TV = findViewById(R.id.wordTwins1)
            word2TV = findViewById(R.id.wordTwins2)
            word3TV = findViewById(R.id.wordTwins3)
            word4TV = findViewById(R.id.wordTwins4)
            word5TV = findViewById(R.id.wordTwins5)
            word6TV = findViewById(R.id.wordTwins6)
            twin1 = findViewById(R.id.twin1)
            twin2 = findViewById(R.id.twin2)
            twin3 = findViewById(R.id.twin3)
            twinA = findViewById(R.id.twinA)
            twinB = findViewById(R.id.twinB)
            twinC = findViewById(R.id.twinC)
            answerResultTV = findViewById(R.id.answerResultTVTwins)
            resultLayout = findViewById(R.id.resultLayoutTwins)
            checkAnswerButton = findViewById(R.id.checkAnswerButtonTwins)
            wordTVS = arrayListOf(word1TV,word2TV,word3TV,word4TV,word5TV,word6TV)
            placeHolderTVS = arrayListOf(twin1,twin2,twin3,twinA,twinB,twinC)

            hebrewPlaceHolders = arrayOf(twin1,twin2,twin3)
            englishPlaceHolders = arrayOf(twinA,twinB,twinC)
        }


    }
    fun setUpObserver(){
        fragmentReady.observe(viewLifecycleOwner, Observer {
            if(it){
                getPositions()
            }
        })

        numbersFiledLD.observe(viewLifecycleOwner, Observer {
            if(it==6){
                setCheckButtonEnabled(true)
            }else{
                setCheckButtonEnabled(false)
            }
        })
    }

    fun setUpQuestion(){
        var TVs = wordTVS
        TVs.shuffle()
        for(i in 0..5){
            TVs[i].text = question.twinWords!![i]
        }
    }

    fun setUpClickEvents(){
       shortClicks()
       longClicks()
    }

    fun longClicks(){
        word1TV.setOnLongClickListener {
            crateDragableObject(word1TV,0)
            true
        }
        word2TV.setOnLongClickListener {
            crateDragableObject(word2TV,1)
            true
        }
        word3TV.setOnLongClickListener {
            crateDragableObject(word3TV,2)
            true
        }
        word4TV.setOnLongClickListener {
            crateDragableObject(word4TV,3)
            true
        }
        word5TV.setOnLongClickListener {
            crateDragableObject(word5TV,4)
            true
        }
        word6TV.setOnLongClickListener {
            crateDragableObject(word6TV,5)
            true
        }
    }

    fun crateDragableObject(view: TextView, num:Int){
        val clipText = view.text.toString()
        val item = ClipData.Item(clipText)
        val mimTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        val data = ClipData(clipText,mimTypes,item)

        val dragShadowBuilder = View.DragShadowBuilder(view)
        view.startDragAndDrop(data,dragShadowBuilder,view,0)
        view.visibility = View.INVISIBLE
    }

    fun showSpots(){
        var str = ""
        for(word in placesFilled){
            if(word==null){
                str+="null,"
            }else {
                str += "${word.text.toString()},"
            }
        }
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
    }


    fun shortClicks(){
        twin1.setOnClickListener {
            animateBack(twin1,twin1.text.toString())
        }
        twin2.setOnClickListener {
            animateBack(twin2,twin2.text.toString())
        }
        twin3.setOnClickListener {
            animateBack(twin3,twin3.text.toString())
        }
        twinA.setOnClickListener {
            animateBack(twinA,twinA.text.toString())
        }
        twinB.setOnClickListener {
            animateBack(twinB,twinB.text.toString())
        }
        twinC.setOnClickListener {
            animateBack(twinC,twinC.text.toString())
        }

    }

    fun animateBack(twin:TextView,word:String){
        var num = placeHolderTVS.indexOf(twin)
        var wordTV = wordTVS[num]
        if(placesFilled[num]!=null) {
            wordTV.visibility = View.VISIBLE
            twin.text = ""
            twin.background = resources.getDrawable(R.drawable.button_shape_gray)
            placesFilled[num]=null
            //showSpots()
            upDateNumbersFilled(false)
        }

    }

    fun getPositions(){
        for (word in wordTVS)
            buttonOriganalPositions.add(ButtonPosition(word.x, word.y))

        for(plh in placeHolderTVS){
            placeHolderPositions.add(ButtonPosition(plh.x,plh.y))
            plh.setOnDragListener(dragListener)
        }
    }

    fun checkAnswer(){
        if(!checkButtonPressed) {
            var numbersOfRightPairs = arrayListOf<Int>()
            var numbersOfWorngPairs = arrayListOf<Int>()
            var answersRight = 0
            var myAnswer1 = placesFilled[0]!!.text.toString()+placesFilled[3]!!.text.toString()
            var myAnswer2 = placesFilled[1]!!.text.toString()+placesFilled[4]!!.text.toString()
            var myAnswer3 = placesFilled[2]!!.text.toString()+placesFilled[5]!!.text.toString()
            if(answers.contains(myAnswer1)) {
                answersRight++
                numbersOfRightPairs.add(0)
            }
            else{
                numbersOfWorngPairs.add(0)
            }
            if(answers.contains(myAnswer2)) {
                answersRight++
                numbersOfRightPairs.add(2)
            } else{
                numbersOfWorngPairs.add(2)
            }
            if(answers.contains(myAnswer3)) {
                answersRight++
                numbersOfRightPairs.add(4)
            } else{
                numbersOfWorngPairs.add(4)
            }

            answerResultTV.visibility = View.VISIBLE
            if (answersRight>1) {
                viewModel.xpPointsNow+=question.xpPoints
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
            for(i in numbersOfRightPairs){
                placesFilled[i]!!.background = resources.getDrawable(R.drawable.button_shape_green)
                placesFilled[i+1]!!.background = resources.getDrawable(R.drawable.button_shape_green)
            }
            for(i in numbersOfWorngPairs){
                placesFilled[i]!!.background = resources.getDrawable(R.drawable.button_shape_red)
                placesFilled[i+1]!!.background = resources.getDrawable(R.drawable.button_shape_red)
            }
        }else {
            parentFragment.handleAnswer(isCorrectAnswer)
        }
    }

    fun setCheckButtonEnabled(b:Boolean){
        checkAnswerButton.isEnabled = b
    }

    companion object{
        fun newInstance(lesson: Lesson, question: Question):QuestionTwinsFragment{
            return QuestionTwinsFragment(lesson,question)
        }
    }
}