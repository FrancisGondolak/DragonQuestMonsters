package com.example.dragonquestmonsters.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dragonquestmonsters.screens.*


@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.MenuInicial.ruta)

    {
        composable(AppScreens.MenuInicial.ruta) {MenuInicial(navigationController) }
        composable(AppScreens.GuardarMonstruo.ruta) {GuardarMonstruo(navigationController) }
        composable(AppScreens.ModificarMonstruo.ruta) {ModificarMonstruo(navigationController) }
        composable(AppScreens.BorrarMonstruo.ruta) { BorrarMonstruo(navigationController) }
        composable(AppScreens.ConsultarMonstruo.ruta) {ConsultarMonstruo(navigationController) }
        composable(AppScreens.InformeMonstruo.ruta) {InformeMonstruo(navigationController) }
    }
}