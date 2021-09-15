package com.example.medicinereminderapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Apps

class AppsAdapter(private val context: Context, private val list: List<Apps>):
                            RecyclerView.Adapter<AppsAdapter.AppViewHolder>(){

    class AppViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.app_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.app_list,parent,false)
        return AppViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = list[position]
        holder.image.setImageResource(app.imageId)
        holder.itemView.setOnClickListener {
            when(position){
                0 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://play.google.com/store/apps/details?id=nic.goi.aarogyasetu&hl=en_IN&gl=US")), null)

                1 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.phonegap.rxpal&hl=en_IN&gl=US")), null)

                2 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.aranoah.healthkart.plus&hl=en_IN&gl=US")), null)

                3 -> ContextCompat.startActivity(context,
                    Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.teladoc.members&hl=en_IN&gl=US")),
                    null)

                4 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://play.google.com/store/apps/details?id=com.mysugr.android.companion&hl=en_IN&gl=US")), null)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}