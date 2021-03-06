package com.example.medicinereminderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.databinding.FragmentReminderBinding
import com.example.medicinereminderapp.databinding.ReminderLayoutBinding
import com.example.medicinereminderapp.model.Medicine

class ReminderListAdapter(private val onItemClicked: (Medicine) -> Unit):
        ListAdapter<Medicine, ReminderListAdapter.ReminderViewHolder>(DiffCallback){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReminderListAdapter.ReminderViewHolder {
        return ReminderViewHolder(ReminderLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ReminderListAdapter.ReminderViewHolder, position: Int) {
       val medicine = getItem(position)
        holder.itemView.setOnClickListener{
            onItemClicked(medicine)
        }
        holder.bind(medicine)
    }

    class ReminderViewHolder(private val binding: ReminderLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(medicine: Medicine){
            binding.apply {
                medicineReminder.text = medicine.name
                doctorName.text = medicine.doctorName
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Medicine>() {
            override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}