package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Recursos

object LocalRecursosDataProvider {
    val RecursosDefault =getRecursosData(R.string.cafeterias) [0]

    fun getRecursosData(tipo:Int): List<Recursos>{
        val listaCafeterias= listOf(
            Recursos(
                id=1,
                title=R.string.pumpumcafe,
                recursos= R.string.cafeterias,
                imagen = R.drawable.pumpumcafe,
                descripcion = R.string.Lore_ipsum
            ),
            Recursos(
                id=2,
                title=R.string.verde,
                recursos= R.string.cafeterias,
                imagen = R.drawable.travoloverde,
                descripcion = R.string.Lore_ipsum
            )
        )
        val listaParques= listOf(
            Recursos(
                id=1,
                title=R.string.retiro,
                recursos= R.string.parques,
                imagen = R.drawable.parque,
                descripcion = R.string.Lore_ipsum
            ),
            Recursos(
                id=2,
                title=R.string.breogan,
                recursos= R.string.parques,
                imagen = R.drawable.parquebreogan,
                descripcion = R.string.Lore_ipsum
            )
        )
        val listaCentros= listOf(
            Recursos(
                id=1,
                title=R.string.gp2,
                recursos= R.string.CentrosCom,
                imagen = R.drawable.gp2,
                descripcion = R.string.Lore_ipsum
            ),
            Recursos(
                id=2,
                title=R.string.vaguada,
                recursos= R.string.CentrosCom,
                imagen = R.drawable.vaguada,
                descripcion = R.string.Lore_ipsum
            )
        )
        when(tipo){
            R.string.cafeterias->return listaCafeterias
            R.string.parques-> return listaParques
            R.string.CentrosCom-> return listaCentros
            else -> return listaCafeterias
        }
    }

}