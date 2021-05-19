package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Categories

class HomeFragmentAdapter(private val context : Context, private val dataSource : List<Categories>):
        RecyclerView.Adapter<HomeFragmentAdapter.HomeViewHolder>(){

    class HomeViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.image_home)
        val text1 : TextView = view.findViewById(R.id.home_text)
        val text2 : TextView = view.findViewById(R.id.home_text_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false)
        return HomeViewHolder(layout)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = dataSource[position]
        holder.image.setImageResource(item.imageID)

    }

    override fun getItemCount(): Int {
       return dataSource.size
    }
}