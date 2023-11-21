package com.example.mycity.model

import androidx.annotation.StringRes

data class City(
    val id:Int,
    @StringRes val cafeterias: Int,
    @StringRes val parques: Int,
    @StringRes val CentrosCom: Int
)
