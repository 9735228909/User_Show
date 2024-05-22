package com.example.usershow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.usershow.ModelClass.OnClickfetch
import com.example.usershow.ModelClass.UserDataItem

class UserAdapter(
    private var usersList: List<UserDataItem>,
    private val onClickfetch: OnClickfetch
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View, onClickfetch: OnClickfetch) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.txtusername)
        val email: TextView = itemView.findViewById(R.id.txtemail)
        val rootdata: ConstraintLayout = itemView.findViewById(R.id.rootdata)

        init {
            rootdata.setOnClickListener {
                onClickfetch.fetchdata(adapterPosition)
            }
        }

        fun usershow(userDataItem: UserDataItem) {
            username.text = userDataItem.username
            email.text = userDataItem.email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return UserViewHolder(view, onClickfetch)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.usershow(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun updateUsers(users: List<UserDataItem>) {
        usersList = users
        notifyDataSetChanged()
    }
}
