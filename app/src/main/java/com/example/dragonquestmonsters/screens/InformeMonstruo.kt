package com.example.dragonquestmonsters.screens

import androidx.compose.foundation.*
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dragonquestmonsters.R
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun InformeMonstruo(navController: NavHostController) {

    var nombre_coleccion = "monstruos_dragon_quest"
    val db = FirebaseFirestore.getInstance()

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.background),
                contentScale = ContentScale.FillBounds
            )
            .verticalScroll(rememberScrollState())

    ) {

        Spacer(modifier = Modifier.size(250.dp))

        //DECLARAMOS LAS VARIABLES QUE VAN A RECOGER LOS DATOS DE LA CONSULTA CON EL ESTADO REMEMBER

        var familiaLimo by remember { mutableStateOf("") }
        var familiaHumanoide by remember { mutableStateOf("") }

        // PINTAMOS EL RESULTADO DE LA CONSULTA QUE VAMOS A HACER A LA BASE DE DATOS. LO PONEMOS
        //AQUÍ ARRIBA PARA QUE CUANDO APAREZCA LO HAGA ENCIMA DE LOS BOTONES DE LISTAR Y DE
        //MENÚ PRINCIPAL. PONEMOS EL CONDICIONAL PARA QUE NO SE PINTEN VACÍOS LOS CUADRADOS
        //ANTES DE TENER LISTADOS LOS MONSTRUOS

        if (familiaLimo != ""){
            Card(
                modifier = Modifier
                    .padding(3.dp)

            ) {
                Column(
                    modifier = Modifier
                        .border(BorderStroke(3.dp, color = Color.Black))
                        .background(color = Color(0xff07A2F5).copy(alpha = 0.8f))
                ) {
                    Text(
                        text = familiaLimo,
                        fontSize = 20.sp,
                        color = Color(0xffF9D1D3),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(15.dp, 15.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .padding(3.dp)

            ) {
                Column(
                    modifier = Modifier
                        .border(BorderStroke(3.dp, color = Color.Black))
                        .background(color = Color(0xFFF5DD07).copy(alpha = 0.8f))
                ) {
                    Text(
                        text = familiaHumanoide,
                        fontSize = 20.sp,
                        color = Color(0xFFF3075A),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(15.dp, 15.dp)
                    )
                }
            }
        }

        Button(
            onClick = {

                //VACIAMOS LAS VARIABLES PARA QUE, SI PULSAMOS DE NUEVO EL BOTÓN DE LISTAR
                //MONSTRUOS NO SE VAYAN DUPLICANDO HASTA LA SACIEDAD
                familiaLimo = ""
                familiaHumanoide = ""

                // HACEMOS LA CONSULTA A LA COLECCION CON GET
                db.collection(nombre_coleccion)
                    .get()

                    //SI SE CONECTA CORRECTAMENTE
                    // RECORRO TODOS LOS DATOS ENCONTRADOS EN LA COLECCIÓN Y LOS ALMACENAMOS
                    //A CADA UNO EN UNA VARIABLE, SEGÚN SU FAMILIA (LIMO, HUMANOIDE, ETC...)
                    .addOnSuccessListener { resultado ->
                        for (encontrado in resultado) {

                            if (encontrado.getString("familia") == "Limo") {

                                familiaLimo += "Número: ${encontrado.getString("numero")}\n"
                                familiaLimo += "Nombre: ${encontrado.getString("nombre")}\n"
                                familiaLimo += "Familia: ${encontrado.getString("familia")}\n"
                                familiaLimo += "Tamaño: ${encontrado.getString("tamanio")}\n"
                                familiaLimo += "Videojuego: ${encontrado.getString("videojuego")}\n\n"

                            } else if (encontrado.getString("familia") == "Humanoide") {

                                familiaHumanoide += "Número: ${encontrado.getString("numero")}\n"
                                familiaHumanoide += "Nombre: ${encontrado.getString("nombre")}\n"
                                familiaHumanoide += "Familia: ${encontrado.getString("familia")}\n"
                                familiaHumanoide += "Tamaño: ${encontrado.getString("tamanio")}\n"
                                familiaHumanoide += "Videojuego: ${encontrado.getString("videojuego")}\n\n"

                            }

                            //Para crear un HashMap con todos los datos
                            //datos += "${encontrado.id}: ${encontrado.data}\n"

                            //Para crear un HashMap con todos los datos
                            //numero_monstruo += encontrado["numero"].toString()
                            //nombre_monstruo += encontrado["nombre"].toString()
                            //familia_monstruo += encontrado["familia"].toString()
                            //tamanio_monstruo += encontrado["tamanio"].toString()
                            //juego_monstruo += encontrado["videojuego"].toString()

                        }
                    }
                    //SI NO CONECTA CORRECTAMENTE
                    .addOnFailureListener { resultado ->
                        familiaLimo = "La conexión a FireStore no se ha podido completar"
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

            Text(text = "Listar monstruos")
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