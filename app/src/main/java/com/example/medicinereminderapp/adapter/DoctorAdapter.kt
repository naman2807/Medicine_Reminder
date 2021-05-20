package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.model.Doctor

class DoctorAdapter(val context : Context, val dataList : List<Doctor>)
    : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>(){

    class DoctorViewHolder(val view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}