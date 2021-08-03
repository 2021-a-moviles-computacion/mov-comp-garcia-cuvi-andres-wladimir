package com.example.examen01

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView

class AdapterEstudiante (
    private val contexto: EstudiantesActivity,
    private var listaEstudiante: ArrayList<Estudiante>,
    private val recyclerView: RecyclerView
        ) : RecyclerView.Adapter<AdapterEstudiante.MyViewHolder>(){

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
            BaseDeDatos.TablaEstudiante = SQLiteHelper(contexto)
            var idItem = BaseDeDatos.TablaEstudiante!!.consultaListaEstudiantes()[adapterPosition]
            val popup = PopupMenu(contexto, v)
            popup.inflate(R.menu.menu_estudiantes)
            popup.setOnMenuItemClickListener {
                when (it.itemId){
                    //Editar
                    R.id.menuEditarEstudiante -> {
                        val intentExplicito = Intent(contexto,EstudiantesFormularioActualizacion::class.java)
                        intentExplicito.putExtra("id",idItem)
                        contexto.startActivity(intentExplicito)
                        contexto.finish()
                        Toast.makeText(contexto, "Editar estudiante -> ${adapterPosition}", Toast.LENGTH_SHORT).show()
                        true
                    }


                    //Eliminar
                    R.id.menuEliminarEstudiante -> {
                        BaseDeDatos.TablaEstudiante!!.eliminarEstudiantePorId(idItem.id)
                        Toast.makeText(
                            contexto,
                            "Estudiante eliminado -> ${adapterPosition}",
                            Toast.LENGTH_SHORT
                        ).show()

                        contexto.finish()

                        true
                    }



                    else -> true

                }
            }
            popup.show()
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
        holder.idMateriaTextView.text = estudiante.idMateria.toString()
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