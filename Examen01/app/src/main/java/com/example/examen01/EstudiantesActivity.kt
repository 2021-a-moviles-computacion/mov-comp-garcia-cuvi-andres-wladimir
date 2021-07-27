package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class EstudiantesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes)

        var listaEstudiante = arrayListOf<Estudiante>()
/*
        BaseDeDatos.TablaEstudiante = SQLiteHelper(this)


        if (BaseDeDatos.TablaEstudiante != null) {
            listaEstudiante = BaseDeDatos.TablaMateria!!.consultaListaEstudiantes()
        }
*/

        listaEstudiante.add((Estudiante(1,1,"201620469","1726264961","Andres Garcia","Computacion","1995-05-24",true)))

        val recyclerViewEstudiante = findViewById<RecyclerView>(R.id.rvListaEstudiante)

        //Iniciar Recycler View
        iniciarRecyclerView(listaEstudiante,this,recyclerViewEstudiante)
    }


    fun iniciarRecyclerView(
        lista: ArrayList<Estudiante>,
        activity: EstudiantesActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterEstudiante(
            activity,
            // this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

        //registerForContextMenu(recyclerView)



    }
}