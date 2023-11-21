package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Tipos

object LocalTipoDataProvider {
    val defaultTipo= getTiposData()[0]

    fun getTiposData(): List<Tipos>{
        return listOf(
            Tipos(
                id=1,
                nombre = R.string.cafeterias,
                imagen = R.drawable.cafeteria
            ),Tipos(
                id=2,
                nombre = R.string.parques,
                imagen = R.drawable.parque
            ),Tipos(
                id=3,
                nombre = R.string.CentrosCom,
                imagen = R.drawable.centrocom
            )

        )

    }


}