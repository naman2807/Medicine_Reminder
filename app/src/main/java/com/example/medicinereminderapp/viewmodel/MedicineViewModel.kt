package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinereminderapp.database.dao.MedicineDao
import com.example.medicinereminderapp.model.Medicine
import kotlinx.coroutines.launch

class MedicineViewModel(private val medicineDao: MedicineDao): ViewModel() {

    private fun addNewMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineDao.addMedicine(medicine)
        }
    }

    fun addMedicine(name: String, doctorName: String){
        addNewMedicine(Medicine(name, doctorName))
    }
}