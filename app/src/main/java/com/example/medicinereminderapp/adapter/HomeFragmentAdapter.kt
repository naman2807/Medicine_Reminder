package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Categories

class HomeFragmentAdapter(private val context : Context, private val dataSource : List<Categories>):
        RecyclerView.Adapter<HomeFragmentAdapter.HomeViewHolder>(){

    class HomeViewHolder(view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false)
        return HomeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}