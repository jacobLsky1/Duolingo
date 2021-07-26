package com.jacoblip.andriod.duolingo.Views

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.jacoblip.andriod.duolingo.Data.models.User
import com.jacoblip.andriod.duolingo.R
import com.jacoblip.andriod.duolingo.Data.services.MainViewModel
import java.time.LocalDate
import java.util.*
import kotlin.math.PI

@RequiresApi(Build.VERSION_CODES.O)
class ProfileFragment:Fragment() {

    val PICK_IMAGE_REQUEST = 1

    var user: User? = null
    lateinit var viewModel: MainViewModel
    lateinit var nameTv:TextView
    lateinit var dateJoinedTv:TextView
    lateinit var languageTv:TextView
    lateinit var xpPointsTv:TextView
    lateinit var dayStreakTv:TextView
    lateinit var editImageButton:ImageView
    lateinit var userImage:ImageView
    lateinit var ImageUri:Uri

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile,container,false )
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.currentFragment = ProfileFragment.newInstance()
        view.apply{
            nameTv = view.findViewById(R.id.nameTV)
            dateJoinedTv= view.findViewById(R.id.dateJoinedTV)
            languageTv = view.findViewById(R.id.languageLearningTV)
            xpPointsTv = view.findViewById(R.id.xpPointsTV)
            dayStreakTv = view.findViewById(R.id.dayStreak)
            editImageButton = view.findViewById(R.id.editImageButton)
            userImage = view.findViewById(R.id.userImage)
            editImageButton.setOnClickListener {
                editImage()
            }
        }
        setUpObservers()
        return view
    }

    fun setUpObservers(){
        viewModel.user.observe(viewLifecycleOwner, Observer {myuser->
            if(myuser!=null){
                user = myuser
                nameTv.text = user!!.name
                dateJoinedTv.text = user!!.joinedDate
                languageTv.text = user!!.currentLearningLanguage
                xpPointsTv.text = user!!.points.toString()
                Glide.with(this).load(user!!.image.toUri()).into(userImage)
                var date  = LocalDate.now()
                dayStreakTv.text = user!!.dayStreak.toString()
            }
        })
    }

    fun editImage(){
       var intent: Intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent,PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.data!=null)
        {
            ImageUri = data.data!!
            Glide.with(this).load(data.data).into(userImage)
            uploadPicture()
        }
    }

    fun getFileExtention(uri: Uri): String? {
        var cr: ContentResolver = activity?.contentResolver!!
        var mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(uri))

    }
    fun uploadPicture() {
        if (ImageUri != null) {
            var extention = getFileExtention(ImageUri)
            if (extention != null) {
                viewModel.uploadPicture(ImageUri)
            }
        }
    }


    companion object{
        fun newInstance():ProfileFragment{
            return ProfileFragment()
        }
    }
}