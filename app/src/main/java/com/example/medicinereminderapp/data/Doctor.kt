package com.example.medicinereminderapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Doctor(@DrawableRes val imageId : Int, @StringRes val name : Int,
                  @StringRes val designation : Int, @StringRes val description : Int) {
}