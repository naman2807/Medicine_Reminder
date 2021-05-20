package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.model.Doctor

class DoctorAdapter(val context : Context, val dataList : List<Doctor>) {

    class DoctorViewHolder(val view : View) : RecyclerView.ViewHolder(view){

    }
}