package com.example.cloudcomputing_firebasetask1.adapter

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudcomputing_firebasetask1.databinding.UserItemBinding
import com.example.cloudcomputing_firebasetask1.model.User

class UserAdapter(var activity: Activity, var data: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserItemBinding.inflate(activity.layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvName.text = data[position].name
        holder.binding.tvPhone.text = data[position].phone.toString()
        holder.binding.tvCity.text = data[position].city.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}