package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Doctor

class DoctorAdapter(val context : Context, val dataList : List<Doctor>)
    : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>(){

    class DoctorViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.image_doctor)
        val name : TextView = view.findViewById(R.id.text_doctor_name)
        val designation : TextView = view.findViewById(R.id.text_specialization)
        val description : TextView = view.findViewById(R.id.text_doctor_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
       val layout = LayoutInflater.from(context).inflate(R.layout.doctor_list_item, parent, false)
        return DoctorViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val item = dataList[position]
        holder.image.setImageResource(item.imageId)
        holder.name.text = context.resources.getString(item.name)
        holder.designation.text = context.resources.getString(item.designation)
        holder.description.text = context.resources.getString(item.description)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}