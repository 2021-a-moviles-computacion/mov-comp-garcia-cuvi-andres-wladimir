package com.example.moviles_computacion_2021_b

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula (
    private val contexto: Class<*>,
    private val listaEntrenador: List < BEntrenador>,
    private val recyclerView: RecyclerView
        ): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>(){
        inner class MyViewHolder (view: View): RecyclerView.ViewHolder(view){


            val nombreTextView: TextView
            val cedulaTextView: TextView
            val likesTextView: TextView
            val actionButton: Button
            var numeroLikes = 0

            init {
                nombreTextView = view.findViewById(R.id.tvNombre)
                cedulaTextView = view.findViewById(R.id.tvCedula)
                actionButton = view.findViewById(R.id.btnDarLike)
                likesTextView = view.findViewById(R.id.tvLikes)
                actionButton.setOnClickListener{
                    this.anadirLike()
                }
            }

            fun anadirLike(){
                this.numeroLikes = this.numeroLikes + 1
                likesTextView.text = this.numeroLikes.toString()

            }

        }


//    Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,   //Definimos la vista de nuestro recyclerView
                parent,
                false
            )
        return MyViewHolder(itemView)
    }


//    Setear los datos de acada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenador = listaEntrenador[position]
        holder.nombreTextView.text = entrenador.nombre
        holder.cedulaTextView.text = entrenador.descripcion
        holder.actionButton.text = "Like ${entrenador.nombre}"
        holder.likesTextView.text = "0"
    }


//    Tamaño del arreglo
    override fun getItemCount(): Int {
        return listaEntrenador.size
    }
}