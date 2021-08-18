package com.example.deber02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView

class AdaptadorUsuarioLlamada (
    private val contexto: MainActivity,
    private var listaUsuario: ArrayList<UsuarioLlamada>,
    private val recyclerView: RecyclerView
        ):RecyclerView.Adapter<AdaptadorUsuarioLlamada.MyViewMyHolder>() {

    inner class MyViewMyHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nombreLlamada: TextView
        var fechaLLamada: TextView
        var imagenLlamada: CircularImageView
        var estadoLlamada: ImageView
        init {
            nombreLlamada = view.findViewById(R.id.tvNombreLlamada)
            fechaLLamada= view.findViewById(R.id.tvFechaLlamada)
            imagenLlamada = view.findViewById(R.id.ivLlamada)
            estadoLlamada = view.findViewById(R.id.ivEstadoLlamada)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewMyHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.list_llamadas,
            parent,
            false
        )

        return MyViewMyHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewMyHolder, position: Int) {
        val usuario = listaUsuario[position]
        holder.nombreLlamada.text = usuario.nombre
        holder.fechaLLamada.text = usuario.fecha
        holder.imagenLlamada.setImageResource(usuario.imagen)
        holder.estadoLlamada.setImageResource(usuario.estado)
    }

    override fun getItemCount(): Int {
        return listaUsuario.size
    }


}