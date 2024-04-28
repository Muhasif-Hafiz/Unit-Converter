package com.muhasib.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muhasib.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }

    @Composable
    fun UnitConverter() {

        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember {mutableStateOf("") }
        var inputUnit by remember {mutableStateOf("Meter") }
        var outputUnit by remember {mutableStateOf("Meter") }
        var iExpand by remember { mutableStateOf(false) }
        var oExpand by remember { mutableStateOf(false) }
        var iConversionFactor= remember { mutableStateOf(1.00) }
        var oConversionFactor= remember { mutableStateOf(1.00) }

        fun ConvertUnits(){

            val inputValueDouble=inputValue.toDoubleOrNull() ?:0.0
            val ans= (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
            outputValue=ans.toString()

        }


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = inputValue ,
                onValueChange = {
                    inputValue=it
                    ConvertUnits()

                }, label = { Text(text = "Enter Value")})

            Spacer(modifier = Modifier.padding(8.dp))




            Row {
                Box {
                    Button(onClick = { iExpand=true }) {
                        Text(text=inputUnit)
                        Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow DropDown")
                        DropdownMenu(expanded = iExpand,
                            onDismissRequest = {
                                iExpand=false
                            })
                        {
                            DropdownMenuItem(
                                text = { Text(text = "Meter") },

                                onClick = {
                                    iExpand=false
                                    inputUnit="Meter"
                                    iConversionFactor.value=1.0
                                    ConvertUnits()


                                })
                            DropdownMenuItem(
                                text = { Text(text = "Centimeter") },
                                onClick = {
                                    iExpand=false
                                    inputUnit="Centimeter"
                                    iConversionFactor.value=0.01
                                    ConvertUnits()


                                })


                            DropdownMenuItem(text = { Text(text = "Feet") },
                                onClick = {
                                    iExpand=false
                                    inputUnit="Feet"
                                    iConversionFactor.value=0.3048
                                    ConvertUnits()



                                })
                            DropdownMenuItem(text = { Text(text = "Millimeter") },

                                onClick = {
                                    iExpand=false
                                    inputUnit="Millimeter"
                                    iConversionFactor.value=0.001
                                    ConvertUnits()

                                })

                        }

                    }



                }
                Spacer(modifier = Modifier.width(32.dp))
                Box {
                    Button(onClick = { oExpand=true}) {
                        Text(text = outputUnit)

                        Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow DropDown")
                        DropdownMenu(expanded = oExpand, onDismissRequest = {oExpand=false }) {


                            DropdownMenuItem(
                                text = { Text(text = "Meter") },
                                onClick = {
                                    oExpand=false
                                    outputUnit="Meter"
                                    oConversionFactor.value=1.00
                                    ConvertUnits()


                                })
                            DropdownMenuItem(text = { Text(text = "Centimeter") },

                                onClick = {
                                    oExpand=false
                                    outputUnit="Centimeter"
                                    oConversionFactor.value=0.01
                                    ConvertUnits()

                                })
                            DropdownMenuItem(text = { Text(text = "Feet") },

                                onClick = {
                                    oExpand=false
                                    outputUnit="Feet"
                                    oConversionFactor.value=0.3048
                                    ConvertUnits()

                                })
                            DropdownMenuItem(text = { Text(text = "Millimeter") },


                                onClick = {
                                    oExpand=false
                                    outputUnit="Millimeter"
                                    oConversionFactor.value=0.001
                                    ConvertUnits()

                                })

                        }

                    }


                }


            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text ="Result : $outputValue $outputUnit", style = MaterialTheme.typography.labelLarge)
        }


    }


    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()


    }
}