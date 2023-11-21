package com.example.mycity.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.example.mycity.model.Tipos
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.mycity.R

@Composable
fun TiposScreen(
    tipos:List<Tipos>,
    onClick:(Tipos)->Unit,
    modifier: Modifier =Modifier,
    contentPadding: PaddingValues= PaddingValues(0.dp)
    ){
    LazyColumn(
        contentPadding =contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier=modifier.padding(top= dimensionResource(R.dimen.padding_medium))
    ){
        items(tipos, key={tipos -> tipos.id }) { tipo ->
            TiposListItem(
                tipo=tipo,
                onItemClick=onClick
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TiposListItem(
    tipo: Tipos,
    onItemClick: (Tipos) ->Unit,
    modifier: Modifier=Modifier
){
    Card(
        elevation=CardDefaults.cardElevation(),
        modifier=modifier,
        shape= RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(tipo)}
    ){
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .size(
                    dimensionResource(R.dimen.card_image_height)
                )
                ) {
                    TiposListImageItem(
                        tipo = tipo,
                        modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
                    )
                    Column(
                        modifier= Modifier
                            .padding(
                                vertical = dimensionResource(R.dimen.padding_small),
                                horizontal = dimensionResource(R.dimen.padding_medium)
                            )
                            .weight(1f)
                    ){
                        Text(
                            text=stringResource(tipo.nombre),
                            style=MaterialTheme.typography.titleMedium,
                            modifier=Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                        )
                    }
                }
    }
}

@Composable
private fun TiposListImageItem(tipo:Tipos, modifier: Modifier= Modifier){
    Box(
        modifier=modifier
    ){
        Image(
            painter = painterResource(tipo.imagen),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
        )
    }
}