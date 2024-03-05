package com.example.codelabnavigation.uiApp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.codelabnavigation.R
import com.example.codelabnavigation.ui.theme.Purple40
import com.example.codelabnavigation.ui.theme.Rojo
@Composable
fun SelectOptionScreen(
    modifier: Modifier = Modifier,
    radioButtonList: List<String>,
    currentPrice: String,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNavigateNext: () -> Unit = {}
) {
    // Estado para almacenar la opción seleccionada
    var selectedOption by remember { mutableStateOf(radioButtonList[0]) }
    // Estado para controlar si se debe mostrar el mensaje de error
    var showError by remember { mutableStateOf(true) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // opcionesm a elegir con radio buttons
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            radioButtonList.forEach { eachOption ->
                Row(
                    modifier = Modifier.selectable(
                        selected = (selectedOption == eachOption),
                        onClick = {
                            if (selectedOption.isBlank()) {
                                showError = true
                            } else {
                                selectedOption = eachOption
                                onSelectionChanged(selectedOption)
                                showError = false
                            }
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedOption == eachOption),
                        onClick = {
                            if (selectedOption.isBlank()) {
                                showError = true
                            } else {
                                selectedOption = eachOption
                                onSelectionChanged(selectedOption)
                                showError = false
                            }
                        },
                        colors = RadioButtonDefaults.colors(Color.Black),
                        modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium))
                    )
                    // Texto de la opción
                    Text(text = eachOption)
                }
            }
        }

        Divider(
            thickness = dimensionResource(R.dimen.thickness_divider),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )

        //mensajes (error o información)
        if (showError) {
            Text(
                text = "Primero debes seleccionar una opción",
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium)
                ),
                color = Rojo,
                fontWeight = FontWeight.Bold
            )
        } else {
            Text(
                text = "Seleccionaste: $selectedOption",
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium)
                ),
            )
            Text(
                text = "Precio: $currentPrice",
                modifier = Modifier.padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_medium)
                )
            )
        }

        //  botones de cancelar y siguiente
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            // Botón de cancelar
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked,
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Purple40
                )
            ) {
                Text(stringResource(R.string.cancel))
            }

            // Botón de siguiente
            Button(
                modifier = Modifier.weight(1f),
                enabled = showError.not(),
                onClick = onNavigateNext,
                colors = ButtonDefaults.buttonColors(Purple40)
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}