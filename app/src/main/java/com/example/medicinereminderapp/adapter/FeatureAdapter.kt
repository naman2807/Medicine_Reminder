package com.example.medicinereminderapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.R
import com.example.medicinereminderapp.model.Features
import java.net.URI

class FeatureAdapter(private val context: Context, private val list: List<Features>):
    RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    class FeatureViewHolder(view : View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.feature_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.feature_list, parent, false)
        return FeatureViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = list[position]
        holder.image.setImageResource(feature.ImageId)
        holder.itemView.setOnClickListener {
            when(position){
                0 -> startActivity(context, Intent(Intent.ACTION_VIEW)
                            .setData(Uri.parse("https://www.cowin.gov.in/")),null)

                1 -> startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://verify.cowin.gov.in/")),null)

                2 -> startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://main.mohfw.gov.in/")),null)

                3 -> startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://pharmacy.dhani.com/home/home2?utm_source=Google&utm_medium=Search&utm_campaign=SB_Generic_Search_ECPC_Exact_1July&utm_id=13138598554&utm_term=medicine%20online%20shopping%20india&utm_device=c&gclid=CjwKCAjwp_GJBhBmEiwALWBQk4faT4R5zyRelqesD_6yeFPUrj9rkEh3WIgFBPjr8D6ncPxYSYJmxxoCv0UQAvD_BwE")),null)

                4 -> startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://cghs.gov.in/")),null)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}