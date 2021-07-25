package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MateriasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        val listaMateria = arrayListOf<Materia>()
        listaMateria.add(Materia("ICCR201","Arquitectura de computadores",6,"B1",true))
        listaMateria.add(Materia("ICCR733","Aplicaciones Web",6,"A1",true))

        val recyclerViewMateria = findViewById<RecyclerView>(R.id.rvListaMaterias)

        //Iniciar Recycler View
        iniciarRecyclerView(listaMateria,this,recyclerViewMateria)

    }

    fun iniciarRecyclerView(
        lista: List<Materia>,
        activity: MateriasActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterMaterias(
            activity,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()
    }


}