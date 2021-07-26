package com.jacoblip.andriod.duolingo.Views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.models.User
import com.jacoblip.andriod.duolingo.R
import com.jacoblip.andriod.duolingo.Views.ClassroomFragment

class LessonItemAdapter(lessons:Array<Lesson>,callbacks: ClassroomFragment.Callbacks,user:User):RecyclerView.Adapter<LessonItemViewHolder>() {
    val callbacks = callbacks
    val lessons = lessons
    val user = user
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonItemViewHolder {
        when(viewType){
        }
        return LessonItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false))
    }

    override fun getItemCount() = lessons.size

    override fun onBindViewHolder(holder: LessonItemViewHolder, position: Int) {

        var lesson = lessons[position]
        holder.itemView.apply {
            val lessonNameTV: TextView = findViewById(R.id.fragmentLessonLessonName)
            val lessonPassedCircle:FrameLayout = findViewById(R.id.lessonPassedCircle)
            val lessonStageCircle:FrameLayout = findViewById(R.id.lessonStageCircle)
            var lessonIcon: ShapeableImageView = findViewById(R.id.lessonIcon)
            lessonNameTV.text = "Lesson: ${position+1}"

            if(user.canAccessLevel>=position){
                lessonIcon.setBackgroundResource(R.drawable.doulingo_app_image)
            }else{
                lessonIcon.setBackgroundResource(R.drawable.doulingo_app_image_black_and_white)
            }

            when(lesson.studyStage){
                1->{
                    lessonStageCircle.setBackgroundResource(R.drawable.button_shape_green)
                }
                2->{
                    lessonStageCircle.setBackgroundResource(R.drawable.button_shape_yellow)
                }
                3->{
                    lessonStageCircle.setBackgroundResource(R.drawable.button_shape_red)
                }
            }
            if(user.numberOfLessonsPassed>=position)
                lessonPassedCircle.setBackgroundResource(R.drawable.button_shape_passed)
            else
                lessonPassedCircle.setBackgroundResource(R.drawable.button_shape_gray)

            setOnClickListener {
                if(user.canAccessLevel>=position)
                callbacks.onLessonSelected(lesson,position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(position){

        }
        return 0
    }

}