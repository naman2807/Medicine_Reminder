package com.example.medicinereminderapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medicinereminderapp.databinding.FragmentReminderBinding
import com.example.medicinereminderapp.databinding.ReminderLayoutBinding
import com.example.medicinereminderapp.model.Medicine

class ReminderListAdapter(private val onItemClicked: (Medicine) -> Unit):
        ListAdapter<Medicine, ReminderListAdapter.ReminderViewHolder>(DiffCallBack){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReminderListAdapter.ReminderViewHolder {
        return ReminderViewHolder(ReminderLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ReminderListAdapter.ReminderViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class ReminderViewHolder(private val binding: ReminderLayoutBinding): RecyclerView.ViewHolder(binding.root){

    }
}