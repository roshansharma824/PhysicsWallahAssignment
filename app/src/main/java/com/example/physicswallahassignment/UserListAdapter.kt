package com.example.physicswallahassignment

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.user_list.view.*

class UserListAdapter (val activity: Activity): RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    private var userList: UserData? = null


    fun setUserList(userList: UserData?) {
        this.userList = userList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListAdapter.MyViewHolder, position: Int) {
        holder.bind(userList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if(userList == null)return 0
        else return userList?.size!!
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        val name = view.userName
        val subject = view.subject
        val qualification  = view.qualification
        val image = view.profileImage
        fun bind(data: UserDataItem, activity: Activity) {
            name.text = data.name
            subject.text = data.subjects[0]
            qualification.text = data.qualification[0]

            Glide.with(image)
                .load(data.profileImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(image)


        }
    }
}