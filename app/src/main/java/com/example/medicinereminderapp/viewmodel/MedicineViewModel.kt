package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.medicinereminderapp.database.dao.MedicineDao
import com.example.medicinereminderapp.database.dao.UserDao
import com.example.medicinereminderapp.model.Medicine
import kotlinx.coroutines.launch

class MedicineViewModel(private val medicineDao: MedicineDao): ViewModel() {

    private fun addNewMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineDao.addMedicine(medicine)
        }
    }

    fun isEntryValid(): Boolean{

    }

    fun addMedicine(userid: String, name: String, doctorName: String, fromDate: String, toDate: String, time: String){
        addNewMedicine(Medicine(userid, name, doctorName, fromDate, toDate, time))
    }

    fun deleteMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineDao.deleteMedicine(medicine)
        }
    }
}

class MedicineViewModelFactory(private val medicineDao: MedicineDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MedicineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MedicineViewModel(medicineDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}