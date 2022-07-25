package me.dio.cartodevisistas

import android.app.Application
import me.dio.cartodevisistas.data.AppDataBase
import me.dio.cartodevisistas.data.CartaoDeVisitasRepositorio

class App : Application(){
    val dataBase by lazy{AppDataBase.getDatabase(this)}
    val repositorio by lazy{CartaoDeVisitasRepositorio(dataBase.cartaoDao())}
}