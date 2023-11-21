package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recursos (
    val id: Int,
    @StringRes val title: Int,
    @StringRes val recursos: Int,
    @DrawableRes val imagen: Int,
    @StringRes val descripcion: Int
)