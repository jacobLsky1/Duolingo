package com.jacoblip.andriod.duolingo.Views.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.jacoblip.andriod.duolingo.Data.models.User
import com.jacoblip.andriod.duolingo.R

class UserItemAdapter(users:Array<User>):RecyclerView.Adapter<UserItemViewHolder>() {
    val users = users
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        when(viewType){
        }
        return UserItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_rv_item, parent, false))
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val user = users[position]
        holder.itemView.apply {
            var userName :TextView = findViewById(R.id.userName)
            var xpPoints:TextView = findViewById(R.id.userXpPointes)
            var itemNum :TextView = findViewById(R.id.itemNum)
            var userImage : ShapeableImageView =findViewById(R.id.userImage)

            userName.text = user.name
            xpPoints.text = "${user.points} xp Points"
            itemNum.text = "#${position+1}"
        }
    }
}