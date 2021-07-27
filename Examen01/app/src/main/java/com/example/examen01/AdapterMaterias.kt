package com.example.examen01

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView

class AdapterMaterias(
    private val contexto: MateriasActivity,
    private var listaMateria: ArrayList<Materia>,
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
            BaseDeDatos.TablaMateria = SQLiteHelper(contexto)
            var idItem = BaseDeDatos.TablaMateria!!.consultaMateriaTotal()[adapterPosition]
            val popup = PopupMenu(contexto, v)
            popup.inflate(R.menu.menu_materias)
            popup.setOnMenuItemClickListener {
                when (it.itemId) {

                    //Editar
                    R.id.menuEditarMaterias -> {

                        val intentExplicito = Intent(contexto,MateriasFormularioActualizacion::class.java)
                        intentExplicito.putExtra("id",idItem)
                        //contexto.startActivityForResult(intentExplicito,401)
                        contexto.startActivity(intentExplicito)
                      //  contexto.startActivity(Intent(contexto, MateriasFormularioActualizacion::class.java));
                        Toast.makeText(contexto, "Editar clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    //Eliminar
                    R.id.menuEliminarMaterias -> {

                        //BaseDeDatos.TablaMateria!!.eliminarMateriaPorCodigo(idItem.codigo.toString())
                        BaseDeDatos.TablaMateria!!.eliminarMateriaPorId(idItem.id)
                        Toast.makeText(
                            contexto,
                            "Eliminar clicked -- ${adapterPosition}",
                            Toast.LENGTH_SHORT
                        ).show()

                        //contexto.startActivity(Intent(contexto, MateriasActivity::class.java));
                        contexto.finish()
                        //contexto.setResult(Activity.RESULT_OK)
                        //contexto.startActivityForResult(Intent(contexto,MateriasActivity::class.java),400)


                        true
                    }

                    else -> true
                }
            }

            popup.show()
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


}