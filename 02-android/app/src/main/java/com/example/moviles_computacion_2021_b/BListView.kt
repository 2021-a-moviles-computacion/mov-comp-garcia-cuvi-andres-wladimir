package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = arrayListOf<Int>(
            1,
            2,
            3
        )

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,    //Layout precargado por Android (visual)
            arregloNumeros
        )

        val listViewEjemplo = findViewById<ListView>(R.id.ltvEjemplo)
        listViewEjemplo.adapter = adaptador

        val btnAnadirNumero = findViewById<Button>(R.id.btnAnadirNumero)
        btnAnadirNumero.setOnClickListener{anadirItemsAListView(
            1,
            arregloNumeros,
            adaptador
        )}


    }

    fun anadirItemsAListView(
        valor: Int,
        arreglo: ArrayList<Int>,
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()        //Actualiza la interfaz
    }

}