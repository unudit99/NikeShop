package com.arvind.nikeshop.view.slider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class FormViewModel {
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var password by mutableStateOf("")
    var mobileNumber by mutableStateOf("")
    var mobileCountryCode by mutableStateOf("")
    var dateOfBirth by mutableStateOf("")
}