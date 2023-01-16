package com.example.dragonquestmonsters.navigation

sealed class AppScreens(val ruta: String){
    object MenuInicial: AppScreens("MenuInicial")
    object GuardarMonstruo: AppScreens("GuardarMonstruo")
    object ModificarMonstruo: AppScreens("ModificarMonstruo")
    object BorrarMonstruo: AppScreens("BorrarMonstruo")
    object ConsultarMonstruo: AppScreens("ConsultarMonstruo")
    object InformeMonstruo: AppScreens("InformeMonstruo")
}