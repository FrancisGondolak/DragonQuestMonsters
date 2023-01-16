package com.example.dragonquestmonsters.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dragonquestmonsters.R
import com.google.firebase.firestore.FirebaseFirestore
import com.example.dragonquestmonsters.navigation.AppScreens

@Composable
fun BorrarMonstruo(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "monstruos_dragon_quest"
    var numero_monstruo by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )

    ) {

        Spacer(modifier = Modifier.size(250.dp))

            OutlinedTextField(
                value = numero_monstruo,
                onValueChange = { numero_monstruo = it },
                label = { Text("Introduce el número del monstruo") },
                modifier = Modifier.padding(horizontal = 20.dp),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xff1700FF),
                    unfocusedBorderColor = Color(0xff1700FF),
                    focusedLabelColor = Color(0xff1700FF),
                    unfocusedLabelColor = Color(0xffFFFFFF),
                    textColor = Color(0xffFFFFFF)
                )
            )

            Spacer(modifier = Modifier.size(20.dp))

            var mensaje_borrado by remember { mutableStateOf("") }

            Text(text = mensaje_borrado,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )

            Button(

                onClick = {
                    if (numero_monstruo.isNotBlank()) {
                        db.collection(nombre_coleccion)
                            .document(numero_monstruo)
                            .delete()
                            .addOnSuccessListener {
                                mensaje_borrado = "El monstruo ha sido borrado"
                                numero_monstruo = ""
                            }
                            .addOnFailureListener {
                                mensaje_borrado = "No se ha podido borrar el monstruo"
                                numero_monstruo = ""
                            }
                    }else{
                        mensaje_borrado = "Ese monstruo no está registrado"
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff07A2F5),
                    contentColor = Color(0xffF9D1D3)
                ),
                border = BorderStroke(3.dp, Color(0xff0032FF)),
                shape = RoundedCornerShape(50)
            )
            {

                Text(text = "Borrar monstruo")


            }
        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = {
                navController.navigate("MenuInicial")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xff07A2F5),
                contentColor = Color(0xffF9D1D3)
            ),
            border = BorderStroke(3.dp, Color(0xff0032FF)),
            shape = RoundedCornerShape(50)
        )
        {
            Text(text = "Menú Inicial")
        }

    }
}