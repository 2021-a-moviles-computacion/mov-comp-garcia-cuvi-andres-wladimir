package com.example.examen01

import android.app.Notification
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class AdapterMaterias(
    private val contexto: MateriasActivity,
    private val listaMateria: List<Materia>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<AdapterMaterias.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreTextView: TextView
        val codigoTextView: TextView
        val aulaTextView: TextView
        val creditosTextView: TextView
        val estadoTextView: TextView
        //


        init {
            nombreTextView = view.findViewById(R.id.tvNombreMateria)
            codigoTextView = view.findViewById(R.id.tvCodigoMateria)
            aulaTextView = view.findViewById(R.id.tvAulaMateria)
            creditosTextView = view.findViewById(R.id.tvCreditosMateria)
            estadoTextView = view.findViewById(R.id.tvEstadoMateria)
            //linearLayoutId = view.findViewById(R.id.LinearLayoutIdMaterias)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_list_materias,
            parent,
            false
        )

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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

    fun removeItem(position: Int) {
        //listaMateria.remove()
    }
}