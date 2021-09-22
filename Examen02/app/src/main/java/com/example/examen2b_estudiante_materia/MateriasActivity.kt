package com.example.examen2b_estudiante_materia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_estudiante_materia.dto.MateriaDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MateriasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        val recyclerViewMateria = findViewById<RecyclerView>(R.id.rvListaMaterias)

        //     Botón para ir al Formulario de Registro de Materias
        val btnIrAFormularioRegistroMaterias = findViewById<Button>(
            R.id.btnInsertarMaterias
        )

        btnIrAFormularioRegistroMaterias.setOnClickListener{
            abrirActividad(MateriasFormularioRegistro::class.java)
            this.finish()
        }

        var registroMateria: (MutableList<DocumentSnapshot>)

        var listaMateria = ArrayList<MateriaDto>()

        val db = Firebase.firestore

        val referenciaMateria = db.collection("materia")

        referenciaMateria
            .get()
            .addOnSuccessListener {
                registroMateria = it.documents
                registroMateria.forEach{ iteracion ->
                    var objMateria = iteracion.toObject(MateriaDto::class.java)
                    objMateria!!.uid = iteracion.id
                    objMateria!!.codigo = iteracion.get("codigo").toString()
                    objMateria!!.nombre = iteracion.get("nombre").toString()
                    objMateria!!.aula = iteracion.get("aula").toString()
                    objMateria!!.creditos = iteracion.get("creditos").toString().toInt()
                    objMateria!!.materiaActiva = iteracion.get("estado").toString().toBoolean()

                    listaMateria.add(objMateria)
                }
                //Iniciar Recycler View
                iniciarRecyclerView(listaMateria,this,recyclerViewMateria)
            }


    }
    fun iniciarRecyclerView(
        lista: ArrayList<MateriaDto>,
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

        //registerForContextMenu(recyclerView)



    }

    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito)
    }
}