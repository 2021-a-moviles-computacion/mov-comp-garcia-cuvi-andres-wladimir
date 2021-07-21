package com.example.examen01.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMaterias ():RecyclerView.Adapter<AdapterMaterias.MyViewHolder>(){
    inner class MyViewHolder (view: View): RecyclerView.ViewHolder(view) {
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
            actionButton.setOnClickListener {
                this.anadirLike()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}