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
fun ConsultarMonstruo(navController: NavHostController) {

    var nombre_coleccion = "monstruos_dragon_quest"
    var no_encontrado by remember { mutableStateOf("") }
    val db = FirebaseFirestore.getInstance()

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

        //DECLARAMOS LA VARIABLE QUE VA A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER
        var datos by remember { mutableStateOf("") }
        var numero_monstruo by remember { mutableStateOf("") }
        var nombre_monstruo by remember { mutableStateOf("") }
        var familia_monstruo by remember { mutableStateOf("") }
        var tamanio_monstruo by remember { mutableStateOf("") }
        var juego_monstruo by remember { mutableStateOf("") }

        var nombre_busqueda by remember { mutableStateOf("") }
        val campo_busqueda ="nombre"
        OutlinedTextField(
            value = nombre_busqueda,
            onValueChange = { nombre_busqueda = it },
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

        Spacer(modifier = Modifier.size(20.dp))

        Text (text = no_encontrado,
            color = Color(0xffFFFFFF),
            fontWeight = FontWeight.ExtraBold
            )

        Spacer(modifier = Modifier.size(5.dp))

        // PINTAMOS EL RESULTADO DE LA CONSULTA A LA BASE DE DATOS

        if (datos.isNotEmpty() && datos.isNotBlank()) {

            Text (text = "Número: " + numero_monstruo,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "Nombre: " + nombre_monstruo,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "Familia: " + familia_monstruo,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "Tamaño: " + tamanio_monstruo,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
            Text (text = "Videojuego: " + juego_monstruo,
                color = Color(0xffFFFFFF),
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.size(5.dp))

        Button(
            onClick = {

                // VACIAMOS VARIABLE AL DAR AL BOTON
                datos = ""
                numero_monstruo = ""
                nombre_monstruo = ""
                familia_monstruo = ""
                tamanio_monstruo = ""
                juego_monstruo = ""
                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection(nombre_coleccion)
                    .whereEqualTo(campo_busqueda,nombre_busqueda)
                    .get()

                    //SI SE CONECTA CORRECTAMENTE
                    // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENO EN DATOS
                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {
                            //Para crear un HashMap con todos los datos
                            datos += "${encontrado.id}: ${encontrado.data}\n"

                            //Para crear un HashMap con todos los datos
                            numero_monstruo += encontrado["numero"].toString()
                            nombre_monstruo += encontrado["nombre"].toString()
                            familia_monstruo += encontrado["familia"].toString()
                            tamanio_monstruo += encontrado["tamanio"].toString()
                            juego_monstruo += encontrado["videojuego"].toString()
                        }

                        if (datos.isEmpty()) {
                            no_encontrado = "Ese monstruo no está registrado"
                        }else{
                            no_encontrado = ""
                        }
                    }
                    //SI NO CONECTA CORRECTAMENTE
                    .addOnFailureListener { resultado ->
                        datos = "La conexión a FireStore no se ha podido completar"
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

            Text(text = "Buscar monstruo")
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