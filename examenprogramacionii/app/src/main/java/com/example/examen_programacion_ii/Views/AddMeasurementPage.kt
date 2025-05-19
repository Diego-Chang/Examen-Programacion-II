package com.example.examen_programacion_ii.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen_programacion_ii.DataBase.Measurement
import com.example.examen_programacion_ii.R
import com.example.examen_programacion_ii.ui.MeasurementsViewModel
import java.time.LocalDate
import kotlin.collections.listOf


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AddMeasurementPageUI(
    //Receives an instance of ViewModel and onClick() method.
    measurementsVM: MeasurementsViewModel = viewModel(factory = MeasurementsViewModel.Factory),
    onClick: () -> Unit = {}
) {
    //Gets current context.
    val context = LocalContext.current

    //Values for Measurement object instance creation.
    var Measurement by remember { mutableStateOf("") }
    var Date by remember { mutableStateOf("") }
    var Icon by remember { mutableIntStateOf(0) }
    var Type by remember { mutableStateOf("")}

    //List of text values for Radio Button options.
    val radioOptions = listOf(context.getString(R.string.type_1_gas),
                        context.getString(R.string.type_2_water),
                        context.getString(R.string.type_3_elec))

    //Values that have their value based on which Radio Button is selected.
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    //Gives values based on Radio Button selected.
    if (selectedOption == context.getString(R.string.type_1_gas)) {
        Icon = R.drawable.flame
        Type = context.getString(R.string.type_1_gas)
    }
    else if ( selectedOption == context.getString(R.string.type_2_water)) {
        Icon = R.drawable.water
        Type = context.getString(R.string.type_2_water)
    }
    else {
        Icon = R.drawable.electricity
        Type = context.getString(R.string.type_3_elec)
    }

    //Creates new Measurement instance and
    // calls to ViewModel's insertDB() method to add it to local DB.
    fun createAndAddMeasurement() {
        val measurement = Measurement(null, Icon, Type, Measurement.toInt(), Date)
        measurementsVM.insertDB(measurement)
    }

    //Page'sUI structure.
    //Receives a TopAppBar as parameter.
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(context.getString(R.string.add_register_title))
                },
                navigationIcon = {
                    IconButton(onClick = { onClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back to Home Page"
                        )
                    }
                }
            )
        }
    ) { innerPadding -> //False Positive.
        Column(
            modifier = Modifier.padding(horizontal = 70.dp, vertical = 200.dp)
        ) {
            Row(){
                OutlinedTextField(
                    value = Measurement,
                    //Ensures input are only number digits.
                    onValueChange = {m ->
                        if (m.all { it.isDigit() }) {
                            Measurement = m} },
                    label = {Text(context.getString(R.string.button_meter))},
                    placeholder = {Text("0", color = Color.Gray)},
                    //When used in a mobile device, the keyboard will only show a number pad.
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            Row(){
                OutlinedTextField(
                    value = Date,
                    onValueChange = {Date = it},
                    label = {Text(context.getString(R.string.button_date))},
                    //Placeholder equals current date.
                    placeholder = {Text("${LocalDate.now()}", color = Color.Gray)}
                )
            }
            Row(
                modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
            ) {
                Text(context.getString(R.string.label_meter_type))
            }
            Row(){

                //radioOptions is read by options and gives values to each part of the Radio Buttons.
                //The value of selectedOption will depend on the button pressed, based on options.
                Column(modifier = Modifier.selectableGroup()) {
                    radioOptions.forEach { options ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                                .selectable(
                                    selected = (options == selectedOption),
                                    onClick = { onOptionSelected(options) },
                                    role = Role.RadioButton
                                )
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (options == selectedOption),
                                onClick = null
                            )
                            Text(
                                text = options,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Button(onClick = { onClick(); createAndAddMeasurement() }) {
                    Text(context.getString(R.string.add_button))
                }
            }
        }
    }
}






