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
import com.example.medicinereminderapp.model.Directions

class DirectionsAdapter(private val context: Context, private val list: List<Directions>):
                            RecyclerView.Adapter<DirectionsAdapter.DirectionViewHolder>(){

    class DirectionViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.direction_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DirectionViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.directions_list, parent, false)
        return DirectionViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DirectionViewHolder, position: Int) {
        val direction = list[position]
        holder.image.setImageResource(direction.ImageId)
        holder.itemView.setOnClickListener {
            when(position){
                0 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://www.google.co.in/maps")), null)

                1 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://www.google.com/search?tbs=lf:1,lf_ui:2&tbm=lcl&q=hospitals&rflfq=1&num=10&ved=2ahUKEwi6p9vkyfbyAhUKzjgGHQ6SBsEQtgN6BAgWEAQ#rlfi=hd:;si:;mv:[[27.655739399999998,77.73098039999999],[27.1982438,77.43878720000001]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:2")), null)

                2 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://www.google.com/search?q=medical+shop+near+me&npsic=0&rflfq=1&rldoc=1&rllag=27613694,77587823,1027&tbm=lcl&sa=X&sqi=2&ved=2ahUKEwiLuYD6yfbyAhU7rZUCHeCiAt4QtgN6BAgEEEE&biw=1536&bih=703#rlfi=hd:;si:;mv:[[27.657329999999998,77.7052631],[27.525112099999998,77.5414692]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:10")), null)

                3 -> ContextCompat.startActivity(context,
                    Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://www.google.com/search?tbs=lf:1,lf_ui:10&tbm=lcl&q=medicine+buyer+shop&rflfq=1&num=10&ved=2ahUKEwjnudm6wvbyAhX1wzgGHQfcCp4QtgN6BAgNEAU#rlfi=hd:;si:;mv:[[29.5439182,78.850985],[26.8145115,77.0129948]];tbs:lrf:!1m4!1u3!2m2!3m1!1e1!1m4!1u2!2m2!2m1!1e1!2m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:10")),
                    null)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}