package com.example.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mycity.R
import com.example.mycity.utils.CityContentType

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


enum class MyCityScreen(@StringRes val title: Int) {
    Tipos(title = R.string.tipos),
    Recursos(title= R.string.Recursos),
    Final(title= R.string.finaliza)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBar(
    @StringRes currentScreen: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    windowSize:WindowWidthSizeClass,
) {
val viewModel : MyCityViewModel = viewModel()
    val navController=rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Tipos.name
    )

    val contentType= when (windowSize){
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> CityContentType.ListOnly
        WindowWidthSizeClass.Expanded -> CityContentType.ListAndDetail
        else ->CityContentType.ListOnly
    }

    Scaffold(
        topBar = {
            CityBar(
                currentScreen = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiStateTipos by viewModel.uiStateTipos.collectAsState()
        val uiStateRecursos by viewModel.uiStateRecursos.collectAsState()

        if (contentType == CityContentType.ListAndDetail) {
            CityListAndDetail(
                uiStateTipos,
                uiStateRecursos,
                viewModel,
                innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            NavHost(
                navController = navController,
                startDestination = MyCityScreen.Tipos.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = MyCityScreen.Tipos.name) {
                    TiposScreen(
                        tipos = uiStateTipos.tiposList,
                        onClick = {
                            viewModel.updateCurrentTipo(it)
                            viewModel.updateRecursosList(it.nombre)
                            navController.navigate(MyCityScreen.Recursos.name)
                        },
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        contentPadding = innerPadding
                    )
                }
                composable(route = MyCityScreen.Recursos.name) {
                    RecursosScreen(
                        recursos = uiStateRecursos.recursosList,
                        onClick = {
                            viewModel.updateCurrentRecursos(it)
                            navController.navigate(MyCityScreen.Final.name)
                        },
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        contentPadding = innerPadding
                    )
                }
                composable(route = MyCityScreen.Recursos.name) {
                    TiposScreen(
                        tipos = uiStateTipos.tiposList,
                        onClick = {
                            viewModel.updateCurrentTipo(it)
                            viewModel.updateRecursosList(it.nombre)
                            navController.navigate(MyCityScreen.Recursos.name)
                        },
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        contentPadding = innerPadding
                    )
                }
                composable(route = MyCityScreen.Recursos.name) {
                    RecursosScreen(
                        recursos = uiStateRecursos.recursosList,
                        onClick = {
                            viewModel.updateCurrentRecursos(it)
                            navController.navigate(MyCityScreen.Final.name)
                        },
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        contentPadding = innerPadding
                    )
                }
                composable(route = MyCityScreen.Final.name) {
                    FinalScreen(
                        selectedRecursos = uiStateRecursos.currentRecursos,
                        contentPadding = innerPadding,
                        onBackPressed = {
                            navController.navigate(MyCityScreen.Recursos.name)
                        }
                    )
                }
            }
        }

            }
        }
@Composable
fun CityListAndDetail(
    uiStateTipos: TiposUiState,
    uiStateRecursos: RecursosUiState,
    viewModel: MyCityViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier

) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.25f)
        ) {
            TiposScreen(
                tipos = uiStateTipos.tiposList,
                onClick = {
                    viewModel.updateCurrentTipo(it)
                    viewModel.updateRecursosList(it.nombre)
                },
                modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                contentPadding = innerPadding
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.35f)
        ) {
            RecursosScreen(
                recursos = uiStateRecursos.recursosList,
                onClick = {
                    viewModel.updateCurrentRecursos(it)
                },
                modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                contentPadding = innerPadding
            )
        }

        Column {
            FinalScreen(
                selectedRecursos = uiStateRecursos.currentRecursos,
                contentPadding = innerPadding,
                onBackPressed = {
                }
            )
        }
    }
}

