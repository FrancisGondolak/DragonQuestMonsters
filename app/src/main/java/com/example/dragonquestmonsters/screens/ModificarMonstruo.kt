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

@Composable
fun ModificarMonstruo(navController: NavHostController) {

    val db = FirebaseFirestore.getInstance()

    var nombre_coleccion = "monstruos_dragon_quest"
    var mensaje_confirmacion by remember { mutableStateOf("") }
    var numero_monstruo by remember { mutableStateOf("") }
    var nombre_monstruo by remember { mutableStateOf("") }
    var familia_monstruo by remember { mutableStateOf("") }
    var tamanio_monstruo by remember { mutableStateOf("") }
    var juego_monstruo by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )

    ) {

        Spacer(modifier = Modifier.size(190.dp))

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

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = nombre_monstruo,
            onValueChange = { nombre_monstruo = it },
            label = { Text("Introduce el nombre del monstruo") },
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

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = familia_monstruo,
            onValueChange = { familia_monstruo = it },
            label = { Text("Introduce la familia del monstruo") },
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

        Spacer(modifier = Modifier.size(10.dp))

        OutlinedTextField(
            value = tamanio_monstruo,
            onValueChange = { tamanio_monstruo = it },
            label = { Text("Introduce el tamaño del monstruo") },
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

        Spacer(modifier = Modifier.size(5.dp))

        OutlinedTextField(
            value = juego_monstruo,
            onValueChange = { juego_monstruo = it },
            label = { Text("Introduce el videojuego de origen") },
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

        Spacer(modifier = Modifier.size(5.dp))

        Text(text = mensaje_confirmacion,
            color = Color(0xffFFFFFF),
            fontWeight = FontWeight.ExtraBold
        )

        val dato = hashMapOf(
            "numero" to numero_monstruo.toString(),
            "nombre" to nombre_monstruo.toString(),
            "familia" to familia_monstruo.toString(),
            "tamanio" to tamanio_monstruo.toString(),
            "videojuego" to juego_monstruo.toString()
        )

        Button(

            onClick = {

                if (numero_monstruo.isNotBlank()){

                    db.collection(nombre_coleccion)
                        .document(numero_monstruo)
                        .set(dato)
                        .addOnSuccessListener {
                            mensaje_confirmacion ="El monstruo ha sido modificado"
                            numero_monstruo =""
                            nombre_monstruo=""
                            familia_monstruo=""
                            juego_monstruo=""
                            tamanio_monstruo=""
                        }
                        .addOnFailureListener {
                            mensaje_confirmacion ="No se ha podido modificar el monstruo"
                            numero_monstruo =""
                            nombre_monstruo=""
                            familia_monstruo=""
                            juego_monstruo=""
                            tamanio_monstruo=""
                        }
                }else{
                    mensaje_confirmacion = "Debes rellenar todos los campos"
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

            Text(text = "Modificar")


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