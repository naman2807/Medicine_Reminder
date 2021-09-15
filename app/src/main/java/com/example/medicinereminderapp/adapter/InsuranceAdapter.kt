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
import com.example.medicinereminderapp.model.Insurance

class InsuranceAdapter(private val context: Context, private val list: List<Insurance>):
                    RecyclerView.Adapter<InsuranceAdapter.InsuranceViewHolder>(){

    class InsuranceViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image = view.findViewById<ImageView>(R.id.insurance_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsuranceViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.insurance_list, parent, false)
        return InsuranceViewHolder(layout)
    }

    override fun onBindViewHolder(holder: InsuranceViewHolder, position: Int) {
        val insurance = list[position]
        holder.image.setImageResource(insurance.ImageId)
        holder.itemView.setOnClickListener {
            when(position){
                0 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://termlife.policybazaar.com/?pb_source=google&pb_medium=cpc&pb_term=Life%20insurance&pb_campaign=Core_Search_Partners00Life_Insurance&gclid=CjwKCAjwp_GJBhBmEiwALWBQk_lNxCJ54yqmOWt8FhlZneqA4zaSXyb8YngQyjiSWfMH8O_8ChDg1xoC5l4QAvD_BwE")), null)

                1 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://www.hdfcergo.com/health-insurance")), null)

                2 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://www.iciciprulife.com/protection-saving-plans/icici-pru-guaranteed-income-for-tomorrow-calculator.html?persona=LumpSum-Child&UID=36484&cid=Search:Google::Text:DM:GIFT:GIFT-Search-Generic-Dualbenefitchild-Guaranteed_Benefits_Child_EM-RX-36484::Eng::RSA:36484:cpc::&gclsrc=aw.ds&&keyword=child%20education%20insurance&matchtype=e&gclid=CjwKCAjwp_GJBhBmEiwALWBQk8majwVdb1uQ1Z7F9dZZoDl4olCRG6yZVfMDtgzbvg0MPbzbayqEQxoCcasQAvD_BwE")), null)

                3 -> ContextCompat.startActivity(context,
                    Intent(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://www.icicilombard.com/home-insurance/bharat-griha-raksha-policy?gclid=CjwKCAjwp_GJBhBmEiwALWBQk4jDAvh_b6MW8tXe5O5chYPF45Syqn6PalPPD2QHIEpj1dT2sNOcHRoCmrQQAvD_BwE")),
                    null)

                4 -> ContextCompat.startActivity(context, Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse("https://www.maxbupa.com/health-insurance-plans/get-quote.html?cid=S_Gen_E_India&utm_source=S_Gen_E_India&utm_medium=GoogleSearch&utm_campaign=Max-Bupa-Generic-Exact-India&utm_term=[family_insurance_plan]&ef_id=CjwKCAjwp_GJBhBmEiwALWBQk6cwFSxQpOZmiOvCsZAMfICJsi3MuafPxC9VY_zDipZFpNFinZTe1xoCmG4QAvD_BwE:G:s&s_kwcid=AL!7961!3!485947749070!e!!g!!family%20insurance%20plan&ef_id=CjwKCAjwp_GJBhBmEiwALWBQk6cwFSxQpOZmiOvCsZAMfICJsi3MuafPxC9VY_zDipZFpNFinZTe1xoCmG4QAvD_BwE:G:s&s_kwcid=AL!7961!3!485947749070!e!!g!!family%20insurance%20plan&gclid=CjwKCAjwp_GJBhBmEiwALWBQk6cwFSxQpOZmiOvCsZAMfICJsi3MuafPxC9VY_zDipZFpNFinZTe1xoCmG4QAvD_BwE")), null)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}