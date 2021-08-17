package com.example.deber02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorUsuarioChat (
    private val contexto: MainActivity,
    private var listaUsuario: ArrayList<UsuarioChat>,
    private val recyclerView: RecyclerView
        ): RecyclerView.Adapter<AdaptadorUsuarioChat.MyViewMyHolder>() {

    inner class MyViewMyHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nombreChat: TextView
        var mensajeChat: TextView
        var imagenChat: ImageView
        var fechaChat: TextView
        var numMensahesChat: TextView

        init {
            nombreChat = view.findViewById(R.id.tvNombreChat)
            mensajeChat = view.findViewById(R.id.tvMensajeChat)
            imagenChat = view.findViewById(R.id.avatar)
            fechaChat = view.findViewById(R.id.tvFechaChat)
            numMensahesChat = view.findViewById(R.id.tvNumMensajesChat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewMyHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.list_chats,
            parent,
            false
        )

        return MyViewMyHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewMyHolder, position: Int) {
        val usuario = listaUsuario[position]
        holder.nombreChat.text = usuario.nombre
        holder.mensajeChat.text = usuario.chat
        holder.fechaChat.text = usuario.fecha
        holder.numMensahesChat.text = usuario.numMensajes.toString()
        holder.imagenChat.setImageResource(usuario.imagen)
    }

    override fun getItemCount(): Int {
        return listaUsuario.size
    }
}
