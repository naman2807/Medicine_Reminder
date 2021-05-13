package com.example.medicinereminderapp.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Affirmation

class AffirmationFragmentAdapter(private val context: Context, private val dataList : List<Affirmation>) {

    class AffirmationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview : TextView = view.findViewById(R.id.item_title)
        val image : ImageView = view.findViewById(R.id.item_image)
    }
}