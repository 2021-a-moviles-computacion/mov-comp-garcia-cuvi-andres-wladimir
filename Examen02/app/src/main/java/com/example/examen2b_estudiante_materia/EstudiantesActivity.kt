package com.example.examen2b_estudiante_materia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_estudiante_materia.dto.EstudianteDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EstudiantesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes)

        val recyclerViewEstudiante = findViewById<RecyclerView>(R.id.rvListaEstudiante)

        //     Botón para ir al Formulario de Registro de Estudiantes0
        val btnIrAFormularioRegistroEstudiante = findViewById<Button>(
            R.id.btnInsertarEstudiante
        )

        btnIrAFormularioRegistroEstudiante.setOnClickListener {
            startActivity(Intent(this,EstudiantesFormularioRegistro::class.java))
            this.finish()
        }

        var registroEstudiante: (MutableList<DocumentSnapshot>)
        var listaEstudiante = ArrayList<EstudianteDto>()
        val db = Firebase.firestore

        val referenciaEstudiante = db.collection("estudiante")

        referenciaEstudiante
            .get()
            .addOnSuccessListener {
                registroEstudiante = it.documents
                registroEstudiante.forEach{iteracion ->
                    var objEstudiante = iteracion.toObject(EstudianteDto::class.java)
                    objEstudiante!!.uid = iteracion.id
                    objEstudiante!!.carrera=iteracion.get("carrera").toString()
                    objEstudiante!!.cedula=iteracion.get("cedula").toString()
                    objEstudiante!!.estadoEstudiante=iteracion.get("estado").toString().toBoolean()
                    objEstudiante!!.fechaNacimiento=iteracion.get("fecha").toString()
                    objEstudiante!!.idMateria=iteracion.get("materia").toString()
                    objEstudiante!!.nombre=iteracion.get("nombre").toString()
                    objEstudiante!!.numeroUnico=iteracion.get("numeroUnico").toString()

                    listaEstudiante.add(objEstudiante)
                }
                //Iniciar Recycler View
                iniciarRecyclerView(listaEstudiante,this,recyclerViewEstudiante)
            }

    }
    fun iniciarRecyclerView(
        lista: ArrayList<EstudianteDto>,
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