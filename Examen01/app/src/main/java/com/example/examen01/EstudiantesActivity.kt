package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class EstudiantesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes)

        var listaEstudiante = arrayListOf<Estudiante>()
        BaseDeDatos.TablaEstudiante = SQLiteHelper(this)
        if (BaseDeDatos.TablaEstudiante != null) {
            listaEstudiante = BaseDeDatos.TablaEstudiante!!.consultaListaEstudiantes()
        }

//        listaEstudiante.add((Estudiante(1,1,"201620469","1726264961","Andres Garcia","Computacion","1995-05-24",true)))
//        listaEstudiante.add((Estudiante(1,1,"215046454","1726264953","Daniel Garcia","Musica","1995-05-24",true)))
//        listaEstudiante.add((Estudiante(1,1,"215046454","1726264953","Daniel Garcia","Musica","1995-05-24",true)))
//        listaEstudiante.add((Estudiante(1,1,"215046454","1726264953","Daniel Garcia","Musica","1995-05-24",true)))
//        listaEstudiante.add((Estudiante(1,1,"215046454","1726264953","Daniel Garcia","Musica","1995-05-24",true)))
//        listaEstudiante.add((Estudiante(1,1,"215046454","1726264953","Jaime  Garcia","Musica","1995-05-24",true)))

        val recyclerViewEstudiante = findViewById<RecyclerView>(R.id.rvListaEstudiante)

        //Iniciar Recycler View
        iniciarRecyclerView(listaEstudiante,this,recyclerViewEstudiante)

        //     Botón para ir al Formulario de Registro de Estudiantes0
        val btnIrAFormularioRegistroEstudiante = findViewById<Button>(
            R.id.btnInsertarEstudiante
        )

        btnIrAFormularioRegistroEstudiante.setOnClickListener {
            startActivity(Intent(this,EstudiantesFormularioRegistro::class.java))
            this.finish()
        }
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

    }
}