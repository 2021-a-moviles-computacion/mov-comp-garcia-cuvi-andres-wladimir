package com.example.examen2b_estudiante_materia

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.examen2b_estudiante_materia.dto.EstudianteDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdapterEstudiante(
    private val contexto: EstudiantesActivity,
    private var listaEstudiante: ArrayList<EstudianteDto>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterEstudiante.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idMateriaTextView: TextView
        var codigoUnicoTextView: TextView
        var cedulaTextView: TextView
        var nombreTextView: TextView
        var carreraTextView: TextView
        var fechaNacimientoTextView: TextView
        var estadoTextView: TextView
        var linearLayoutId: LinearLayoutCompat

        init {
            idMateriaTextView = view.findViewById(R.id.tvidMateriaEstudiante)
            codigoUnicoTextView = view.findViewById(R.id.tvCodigoUnicoEstudiante)
            cedulaTextView = view.findViewById(R.id.tvCedulaEstudiante)
            nombreTextView = view.findViewById(R.id.tvNombreEstudiante)
            carreraTextView = view.findViewById(R.id.tvCarreraEstudiante)
            fechaNacimientoTextView = view.findViewById(R.id.tvFechaNacimientoEstudiante)
            estadoTextView = view.findViewById(R.id.tvEstadoEstudiante)
            linearLayoutId = view.findViewById(R.id.LinearLayoutIdEstudiante)
            linearLayoutId.setOnClickListener { popUpMenus(it) }
        }

        private fun popUpMenus(v: View) {
            var registroEstudiante: (MutableList<DocumentSnapshot>)
            val listaEstudiante = ArrayList<EstudianteDto>()
            val db = Firebase.firestore

            val referenciaEstudiante = db.collection("estudiante")

            referenciaEstudiante
                .get()
                .addOnSuccessListener {
                    registroEstudiante = it.documents
                    registroEstudiante.forEach { iteracion ->
                        val objEstudiante = iteracion.toObject(EstudianteDto::class.java)
                        objEstudiante!!.uid = iteracion.id
                        objEstudiante.carrera = iteracion.get("carrera").toString()
                        objEstudiante.cedula = iteracion.get("cedula").toString()
                        objEstudiante.estadoEstudiante =
                            iteracion.get("estado").toString().toBoolean()
                        objEstudiante.fechaNacimiento = iteracion.get("fecha").toString()
                        objEstudiante.idMateria = iteracion.get("materia").toString()
                        objEstudiante.latitud = iteracion.get("latitud") as Double?
                        objEstudiante.longitud = iteracion.get("longitud") as Double?
                        objEstudiante.nombre = iteracion.get("nombre").toString()
                        objEstudiante.numeroUnico = iteracion.get("numeroUnico").toString()

                        listaEstudiante.add(objEstudiante)
                    }

                    val idItem =listaEstudiante[adapterPosition]
                    val popup = PopupMenu(contexto, v)
                    popup.inflate(R.menu.menu_estudiantes)
                    popup.setOnMenuItemClickListener {
                        when (it.itemId) {
                            //Editar
                            R.id.menuEditarEstudiante -> {
                                val intentExplicito =
                                    Intent(contexto, EstudiantesFormularioActualizacion::class.java)
                                intentExplicito.putExtra("id", idItem)
                                contexto.startActivity(intentExplicito)
                                contexto.finish()
                                Toast.makeText(
                                    contexto,
                                    "Editar estudiante -> ${adapterPosition}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }


                            //Eliminar
                            R.id.menuEliminarEstudiante -> {

                                val builder = AlertDialog.Builder(contexto)
                                builder.setTitle("ALERTA !! \n Seguro quiere eliminar este estudiante?")
                                builder.setPositiveButton(
                                    "Aceptar",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        referenciaEstudiante.document(idItem.uid.toString())
                                            .delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(
                                                    contexto,
                                                    "Estudiante eliminado -> ${adapterPosition}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                contexto.finish()
                                                contexto.startActivity(
                                                    Intent(
                                                        contexto,
                                                        EstudiantesActivity::class.java
                                                    )
                                                )
                                            }
                                    }
                                )

                                builder.setNegativeButton(
                                    "Cancelar", null
                                )

                                val dialog = builder.create()
                                dialog.show()
                                true
                            }

                            R.id.menuVerUbicacion -> {
                                val intentExplicito =
                                    Intent(contexto, MapsActivity::class.java)
                                intentExplicito.putExtra("id",idItem)
                                contexto.startActivity(intentExplicito)
                                contexto.finish()
                                Toast.makeText(
                                    contexto,
                                    "Abriendo Ubicación",
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }


                            else -> true

                        }
                    }
                    popup.show()
                }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterEstudiante.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_estudiante,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterEstudiante.MyViewHolder, position: Int) {
        val estudiante = listaEstudiante[position]
        holder.idMateriaTextView.text = estudiante.idMateria
        holder.codigoUnicoTextView.text = estudiante.numeroUnico
        holder.cedulaTextView.text = estudiante.cedula
        holder.nombreTextView.text = estudiante.nombre
        holder.carreraTextView.text = estudiante.carrera
        holder.fechaNacimientoTextView.text = estudiante.fechaNacimiento
        holder.estadoTextView.text = estudiante.estadoEstudiante.toString()
    }

    override fun getItemCount(): Int {
        return listaEstudiante.size
    }

}