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
import com.example.examen2b_estudiante_materia.dto.MateriaDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AdapterMaterias(
    private val contexto: MateriasActivity,
    private var listaMateria: ArrayList<MateriaDto>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterMaterias.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreTextView: TextView
        var codigoTextView: TextView
        var aulaTextView: TextView
        var creditosTextView: TextView
        var estadoTextView: TextView
        var linearLayoutId: LinearLayoutCompat


        init {
            nombreTextView = view.findViewById(R.id.tvNombreMateria)
            codigoTextView = view.findViewById(R.id.tvCodigoMateria)
            aulaTextView = view.findViewById(R.id.tvAulaMateria)
            creditosTextView = view.findViewById(R.id.tvCreditosMateria)
            estadoTextView = view.findViewById(R.id.tvEstadoMateria)
            linearLayoutId = view.findViewById(R.id.LinearLayoutIdMaterias)
            linearLayoutId.setOnClickListener { popUpMenus(it) }
        }

        private fun popUpMenus(v: View) {
            var registroMateria: (MutableList<DocumentSnapshot>)

            val listaMateria = ArrayList<MateriaDto>()

            val db = Firebase.firestore
            val referenciaMateria = db.collection("materia")
            referenciaMateria
                .get()
                .addOnSuccessListener {
                    registroMateria = it.documents
                    registroMateria.forEach { iteracion ->
                        val objMateria = iteracion.toObject(MateriaDto::class.java)
                        objMateria!!.uid = iteracion.id
                        objMateria.codigo = iteracion.get("codigo").toString()
                        objMateria.nombre = iteracion.get("nombre").toString()
                        objMateria.creditos = iteracion.get("creditos").toString().toInt()
                        objMateria.aula = iteracion.get("aula").toString()
                        objMateria.materiaActiva = iteracion.get("estado").toString().toBoolean()

                        listaMateria.add(objMateria)
                    }
                    val idItem = listaMateria[adapterPosition]
                    val popup = PopupMenu(contexto, v)
                    popup.inflate(R.menu.menu_materias)
                    popup.setOnMenuItemClickListener {
                        when (it.itemId) {

                            //Editar
                            R.id.menuEditarMaterias -> {
                                val intentExplicito =
                                    Intent(contexto, MateriasFormularioActualizacion::class.java)
                                intentExplicito.putExtra("id", idItem)
                                //intentExplicito.putExtra("uid", idItem.uid)
                                //contexto.startActivityForResult(intentExplicito,401)
                                contexto.startActivity(intentExplicito)
                                contexto.finish()
                                //  contexto.startActivity(Intent(contexto, MateriasFormularioActualizacion::class.java));
                                Toast.makeText(contexto, "Editar clicked", Toast.LENGTH_SHORT)
                                    .show()
                                true
                            }

                            //Eliminar
                            R.id.menuEliminarMaterias -> {
                                val builder = AlertDialog.Builder(contexto)
                                builder.setTitle("ALERTA !! \n Seguro quiere eliminar esta materia?")
                                builder.setPositiveButton(
                                    "Aceptar",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        referenciaMateria.document(idItem.uid.toString())
                                            .delete()
                                            .addOnSuccessListener {
                                                Toast.makeText(
                                                    contexto,
                                                    "Eliminar clicked -- ${adapterPosition}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                                contexto.finish()
                                                contexto.startActivity(
                                                    Intent(
                                                        contexto,
                                                        MateriasActivity::class.java
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

                            //Lista Estudiantes
                            R.id.menuListaEstudiantes -> {

                                var registroEstudiante: (MutableList<DocumentSnapshot>)
                                val listaEstudiante = ArrayList<EstudianteDto>()
                                val db = Firebase.firestore

                                val referenciaEstudiante = db.collection("estudiante")

                                referenciaEstudiante.whereEqualTo("materia", idItem.uid)
                                    .get()
                                    .addOnSuccessListener {
                                        registroEstudiante = it.documents
                                        registroEstudiante.forEach { iteracion ->
                                            val objEstudiante =
                                                iteracion.toObject(EstudianteDto::class.java)
                                            objEstudiante!!.uid = iteracion.id
                                            objEstudiante.carrera =
                                                iteracion.get("carrera").toString()
                                            objEstudiante.cedula =
                                                iteracion.get("cedula").toString()
                                            objEstudiante.estadoEstudiante =
                                                iteracion.get("estado").toString().toBoolean()
                                            objEstudiante.fechaNacimiento =
                                                iteracion.get("fecha").toString()
                                            objEstudiante.idMateria =
                                                iteracion.get("materia").toString()
                                            objEstudiante.nombre =
                                                iteracion.get("nombre").toString()
                                            objEstudiante.numeroUnico =
                                                iteracion.get("numeroUnico").toString()

                                            listaEstudiante.add(objEstudiante)
                                        }
                                        //Iniciar Recycler View
                                        iniciarRecyclerView(
                                            listaEstudiante,
                                            EstudiantesActivity(),
                                            recyclerView
                                        )
                                    }

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
    ): AdapterMaterias.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_materias,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterMaterias.MyViewHolder, position: Int) {
        val materia = listaMateria[position]
        holder.nombreTextView.text = materia.nombre
        holder.codigoTextView.text = materia.codigo
        holder.aulaTextView.text = materia.aula
        holder.creditosTextView.text = materia.creditos.toString()
        holder.estadoTextView.text = materia.materiaActiva.toString()
    }

    override fun getItemCount(): Int {
        return listaMateria.size
    }

    fun iniciarRecyclerView(
        lista: ArrayList<EstudianteDto>,
        activity: EstudiantesActivity,
        recyclerView: RecyclerView
    ) {
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