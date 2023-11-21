package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.LocalRecursosDataProvider
import com.example.mycity.data.LocalTipoDataProvider
import com.example.mycity.model.Recursos
import com.example.mycity.model.Tipos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel: ViewModel(){
    private val _uiStateTipos= MutableStateFlow(
        TiposUiState(
            tiposList=LocalTipoDataProvider.getTiposData(),
            currentTipos=LocalTipoDataProvider.getTiposData().getOrElse(0){
                LocalTipoDataProvider.defaultTipo
            }
        )
    )
    val uiStateTipos: StateFlow<TiposUiState> = _uiStateTipos

    private val _uiStateRecursos= MutableStateFlow(
        RecursosUiState(
            recursosList= LocalRecursosDataProvider.getRecursosData(uiStateTipos.value.currentTipos.nombre),
            currentRecursos=LocalRecursosDataProvider.getRecursosData(uiStateTipos.value.currentTipos.nombre).getOrElse(0){
                LocalRecursosDataProvider.RecursosDefault
            }
        )
    )
    val uiStateRecursos: StateFlow<RecursosUiState> = _uiStateRecursos

    fun updateCurrentTipo(selectedTipos: Tipos){
        _uiStateTipos.update{
            it.copy(currentTipos=selectedTipos)
        }
    }
    fun updateCurrentRecursos(selectedRecursos: Recursos){
        _uiStateRecursos.update{
            it.copy(currentRecursos=selectedRecursos)
        }
    }
    fun updateRecursosList(tipo:Int){
        _uiStateRecursos.update {
            it.copy(recursosList = LocalRecursosDataProvider.getRecursosData(tipo))
        }
    }

}
data class TiposUiState(
    val tiposList: List<Tipos> = emptyList(),
    val currentTipos: Tipos=LocalTipoDataProvider.defaultTipo
)
data class RecursosUiState(
    val recursosList: List<Recursos> = emptyList(),
    val currentRecursos: Recursos= LocalRecursosDataProvider.RecursosDefault
)