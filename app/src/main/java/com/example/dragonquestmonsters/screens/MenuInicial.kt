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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dragonquestmonsters.R

@Composable
fun MenuInicial(navController: NavHostController) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.background),
                    contentScale = ContentScale.FillBounds
                )

        ) {

            Spacer(modifier = Modifier.size(300.dp))

            Button(
                onClick = {
                    navController.navigate("GuardarMonstruo")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff07A2F5),
                    contentColor = Color(0xffF9D1D3)
                ),
                border = BorderStroke(3.dp, Color(0xff0032FF)),
                shape = RoundedCornerShape(50)
            )
            {
                Text(text = "Registrar monstruo")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("ModificarMonstruo")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff07A2F5),
                    contentColor = Color(0xffF9D1D3)
                ),
                border = BorderStroke(3.dp, Color(0xff0032FF)),
                shape = RoundedCornerShape(50)
            )
            {
                Text(text = "Modificar monstruo")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("BorrarMonstruo")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff07A2F5),
                    contentColor = Color(0xffF9D1D3)
                ),
                border = BorderStroke(3.dp, Color(0xff0032FF)),
                shape = RoundedCornerShape(50)
            )
            {
                Text(text = "Eliminar monstruo")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("ConsultarMonstruo")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff07A2F5),
                    contentColor = Color(0xffF9D1D3)
                ),
                border = BorderStroke(3.dp, Color(0xff0032FF)),
                shape = RoundedCornerShape(50)
            )
            {
                Text(text = "Monstruopedia")
            }

            Spacer(modifier = Modifier.size(8.dp))

            Button(
                onClick = {
                    navController.navigate("InformeMonstruo")
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xff07A2F5),
                    contentColor = Color(0xffF9D1D3)
                ),
                border = BorderStroke(3.dp, Color(0xff0032FF)),
                shape = RoundedCornerShape(50)
            )
            {
                Text(text = "Listado completo")
            }

        }

}