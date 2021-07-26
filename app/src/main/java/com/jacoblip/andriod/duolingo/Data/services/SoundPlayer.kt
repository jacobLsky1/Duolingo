package com.jacoblip.andriod.duolingo.Data.services

import android.content.Context
import android.media.MediaPlayer
import com.jacoblip.andriod.duolingo.R

class SoundPlayer() {
    companion object{
        fun playSound(context: Context,num:Int){
            when(num){
                1->{
                    var mediaPlayer = MediaPlayer.create(context, R.raw.correct_answer_sound)
                    mediaPlayer.start()
                }
                2->{
                    var mediaPlayer = MediaPlayer.create(context, R.raw.wrong_answer_sound)
                    mediaPlayer.start()
                }
                3->{
                    var mediaPlayer = MediaPlayer.create(context, R.raw.out_of_lives_sound)
                    mediaPlayer.start()
                }
                4->{
                    var mediaPlayer = MediaPlayer.create(context, R.raw.lesson_complete_sound)
                    mediaPlayer.start()
                }
            }
        }
    }
}