package com.example.medicinereminderapp.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseActivityViewModel : ViewModel(){

    private var _selectedFragment = MutableLiveData<Fragment>()
    val selectedFragment : LiveData<Fragment> = _selectedFragment

}