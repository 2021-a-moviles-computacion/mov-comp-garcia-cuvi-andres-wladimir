package com.example.deber02

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import java.awt.font.TextAttribute

class AdaptadorUsuarioEstado (
    private val contexto: MainActivity,
    private var listaUsuario: ArrayList<UsuarioEstado>,
    private val recyclerView: RecyclerView
        ): RecyclerView.Adapter<AdaptadorUsuarioEstado.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nombreEstado: TextView
        var fechaEstado: TextView
        var imagenEstado: CircularImageView

        init {
            nombreEstado = view.findViewById(R.id.tvNombreEstado)
            fechaEstado = view.findViewById(R.id.tvFechaEstado)
            imagenEstado = view.findViewById(R.id.ivEstado)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.list_estados,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val usuario = listaUsuario[position]
        holder.nombreEstado.text = usuario.nombre
        holder.fechaEstado.text = usuario.fecha
        holder.imagenEstado.setImageResource(usuario.imagen)
    }

    override fun getItemCount(): Int {
        return listaUsuario.size
    }


}