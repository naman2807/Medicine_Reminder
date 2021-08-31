package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.medicinereminderapp.database.dao.MedicineDao
import com.example.medicinereminderapp.model.Medicine

class MedicineViewModel(private val medicineDao: MedicineDao): ViewModel() {
    private fun addNewMedicine(medicine: Medicine){

    }
}