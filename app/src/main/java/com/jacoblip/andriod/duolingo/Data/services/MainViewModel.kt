 package com.jacoblip.andriod.duolingo.Data.services

import android.content.Context
import android.net.Uri
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jacoblip.andriod.duolingo.Data.models.Lesson
import com.jacoblip.andriod.duolingo.Data.models.Picture
import com.jacoblip.andriod.duolingo.Data.models.User
import io.realm.Realm
import org.jetbrains.anko.doAsync

 const val CLOCK_DAY = 86400000
 private const val TAG = "MainViewModel"
class MainViewModel(repository: Repository,context: Context):ViewModel() {
    var appInit = true
    var repository = repository
    var context = context
    var level : MutableLiveData<Int> = MutableLiveData()
    var currentFragment:Fragment? = null
    var xpPointsNow = 0
    var user: MutableLiveData<User> = MutableLiveData()
    lateinit var myUser :User
    var viewModelLessons : Array<Lesson> = arrayOf()
    var lessons: MutableLiveData<Array<Lesson>> = MutableLiveData()
    var photos: Array<Picture> = arrayOf()
    var listOfLessons: MutableList<Lesson> = mutableListOf()
    var listOfImages: MutableList<Picture> = mutableListOf()
  //  val myStorage = FirebaseStorage.getInstance().getReference("uploads")
    var allUsers:MutableLiveData<Array<User>> = MutableLiveData()
    var lessonInProgress :MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getUserFromLocal()
        getPhotos()
        getLessons()
        getUser()
    }

    fun upDateLogIn(){
        var now  = SystemClock.currentThreadTimeMillis()
        if(now-myUser.lastSignIn.toLong()> CLOCK_DAY&&now-myUser.lastSignIn.toLong()< CLOCK_DAY*2){
            FirebaseDatabase.getInstance().getReference("users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid).child("lastSignIn").setValue(now.toString())

            var num = user.value!!.dayStreak
            num++

            FirebaseDatabase.getInstance().getReference("users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid).child("dayStreak").setValue(num)

        }else{
            FirebaseDatabase.getInstance().getReference("users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid).child("dayStreak").setValue(1)

        }

    }

    fun updateUser(numberOfLesson: Int){
        Log.i("repository", FirebaseAuth.getInstance().currentUser!!.uid)
        var ref = FirebaseDatabase.getInstance().getReference("users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(myUser)
            .addOnSuccessListener {
                xpPointsNow = 0
                getUser()
                if(numberOfLesson==7)
                level.postValue(2)

                if(numberOfLesson==15)
                    level.postValue(3)

                if(numberOfLesson==22)
                    level.postValue(4)
            }
            .addOnFailureListener { Log.i(TAG, "can not update user") }

    }

    fun getUser() {
        Log.i("repository", FirebaseAuth.getInstance().currentUser!!.uid)
        var ref = FirebaseDatabase.getInstance().getReference("users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user.postValue(dataSnapshot.getValue(User::class.java))
                Log.i("repository", user.toString())
                saveUserToLocal(dataSnapshot.getValue(User::class.java)!!)
                myUser = dataSnapshot.getValue(User::class.java)!!
                level.postValue(myUser.level)
                upDateLessonsForUser(myUser.numberOfLessonsPassed)
                if (appInit) {
                    upDateLogIn()
                    appInit = false
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    fun saveUserToLocal(user: User){
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.insert(user)
        realm.commitTransaction()
    }

    fun getUserFromLocal(){
        val realm = Realm.getDefaultInstance()
        val myuser = realm.where(User::class.java).findFirst()
        user.postValue(myuser)
    }

    fun getPhotos() {
        var ref = FirebaseDatabase.getInstance().getReference("appImages")

        val menuListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                // Log.i("","")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot!!.children
                children.forEach {
                    listOfImages.add(it.getValue(Picture::class.java)!!)
                }
                photos = listOfImages.toTypedArray()
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

        fun getLessons() {
            listOfLessons = mutableListOf()
            Log.i("repository", FirebaseAuth.getInstance().currentUser!!.uid)
            var ref = FirebaseDatabase.getInstance().getReference("lessons")

            val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val children = dataSnapshot!!.children
                    children.forEach {
                        listOfLessons.add(it.getValue(Lesson::class.java)!!)
                    }
                    viewModelLessons = listOfLessons.toTypedArray()
                    lessons.postValue(viewModelLessons)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)
        }


        fun uploadPicture(ImageUri: Uri) {
            if (ImageUri != null) {
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().currentUser!!.uid).child("image").setValue(ImageUri.toString())
                        .addOnSuccessListener { getUser() }
                        .addOnFailureListener {Toast.makeText( context,"a problem has occurred.. the image was not uploaded",Toast.LENGTH_SHORT).show() }

            }
        }



    fun  upDateLessonsForUser(numberOfLessonsPassed:Int) {
        if (numberOfLessonsPassed != -1) {
            var num = 0
            for (i in 0..numberOfLessonsPassed) {
                for (j in viewModelLessons[i].quetions.indices) {
                    viewModelLessons[j].quetions[j].answeredCorrect = true
                }

               /* if (!viewModelLessons[i].lessonCompleted) {
                    myUser.numberOfLessonsPassed = myUser.numberOfLessonsPassed!! + 1
                    myUser.canAccessLevel = myUser.canAccessLevel + 1
                    viewModelLessons[i].lessonCompleted = true
                }

                */
            }

        }
    }

    fun upDateLesson(numberOfLesson: Int){
            if (!viewModelLessons[numberOfLesson].lessonCompleted) {
                myUser.numberOfLessonsPassed +=1
                myUser.canAccessLevel+=1
                myUser.points+=xpPointsNow
                viewModelLessons[numberOfLesson].lessonCompleted = true
                updateUser(numberOfLesson)
            }

    }

    fun getUsers(){
        var allAppUsers:ArrayList<User> = arrayListOf()
        var appUser:User? = null
        var ref = FirebaseDatabase.getInstance().getReference("users")

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val children = dataSnapshot.children
                children.forEach{child->
                   appUser = child.getValue(User::class.java)
                    appUser?.let { allAppUsers.add(it) }
                }
                allAppUsers.sort()
                allUsers.postValue(allAppUsers.toTypedArray())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    }