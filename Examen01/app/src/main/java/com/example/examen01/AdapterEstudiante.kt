package com.example.examen01

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
            //linearLayoutId.setOnClickListener { popUpMenus(it) }
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