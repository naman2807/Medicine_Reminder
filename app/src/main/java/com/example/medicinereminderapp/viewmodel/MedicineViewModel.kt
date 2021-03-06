package com.example.medicinereminderapp.viewmodel

import androidx.lifecycle.*
import com.example.medicinereminderapp.database.dao.MedicineDao
import com.example.medicinereminderapp.database.dao.UserDao
import com.example.medicinereminderapp.model.Medicine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MedicineViewModel(private val medicineDao: MedicineDao): ViewModel() {

    private fun addNewMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineDao.addMedicine(medicine)
        }
    }

    fun isAnyFieldEmpty(name: String, doctorName: String, fromDate: String,
                     toDate: String, time: String): Boolean{
        return name.isBlank() || doctorName.isBlank() || fromDate.isBlank()
                || toDate.isBlank() || time.isBlank()

    }

    fun addMedicine(userid: String, name: String, doctorName: String, fromDate: String, toDate: String, time: String){
        addNewMedicine(Medicine(userid, name, doctorName, fromDate, toDate, time))
    }

    fun deleteMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineDao.deleteMedicine(medicine)
        }
    }

    fun getAllMedicines(userid: String): LiveData<List<Medicine>>{
        return medicineDao.getAllMedicines(userid).asLiveData()
    }

    fun getMedicine(name: String): LiveData<Medicine>{
        return medicineDao.getMedicine(name).asLiveData()
    }

    fun updateMedicine(medicine: Medicine){
        viewModelScope.launch {
            medicineDao.updateMedicine(medicine)
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