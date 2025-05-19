package com.example.examen_programacion_ii.Views

import android.icu.text.DecimalFormat
import android.icu.text.DecimalFormatSymbols
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.examen_programacion_ii.R
import com.example.examen_programacion_ii.ui.MeasurementsViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageUI(
    //Receives an instance of ViewModel and onClick() method.
    measurementsVM: MeasurementsViewModel = viewModel(factory = MeasurementsViewModel.Factory),
    onClick: () -> Unit = {}
) {

    //Gets current context.
    val context = LocalContext.current

    //Int formatting.
    fun measurementFormat(number: Int): String {
        val decimalFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.US))
        return decimalFormat.format(number).replace(",", ".")
    }

    //Executes function once on initiation.
    LaunchedEffect(Unit) {
        measurementsVM.getDB()
    }

    //Page'sUI structure.
    //Receives a CentralAlignedTopAppBar and FloatingActionButton as parameters.
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors= topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(context.getString(R.string.home_title),
                        textAlign = TextAlign.Center)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClick() },
                shape = CircleShape,)
            {
                Icon(Icons.Filled.Add, "Large floating action button")
            }
        }
    ) { innerPadding -> //False Positive.
        //Page's body.
        //Has a LazyColumn which receives on items() method the ViewModel's measurements list.
        //m goes through list and hands over values of each item on it
        //to Image and Text Composables.
        LazyColumn(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 120.dp)
        ) {
            items(measurementsVM.measurements) { m ->
                Column() {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(m.icon),
                            contentDescription = "Logo type"
                        )

                        if (m.type != context.getString(R.string.type_1_gas)) {
                            var contextType = context.getString(R.string.type_1_gas)
                        }
                        Text(m.type,
                            fontSize = 20.sp)
                        Text(measurementFormat(m.measurement).toString(),
                            fontSize = 20.sp)
                        Text(m.date.toString(),
                            fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()
                }
            }
        }
    }
}








