package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Affirmation

class AffirmationFragmentAdapter(private val context: Context, private val dataList : List<Affirmation>)
    : RecyclerView.Adapter<AffirmationFragmentAdapter.AffirmationViewHolder>(){

    class AffirmationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview : TextView = view.findViewById(R.id.item_title)
        val image : ImageView = view.findViewById(R.id.item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AffirmationViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return AffirmationViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AffirmationViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}