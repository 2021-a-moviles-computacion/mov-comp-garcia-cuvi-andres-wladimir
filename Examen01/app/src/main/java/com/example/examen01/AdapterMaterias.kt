package com.example.examen01

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
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

                        val intentExplicito =
                            Intent(contexto, MateriasFormularioActualizacion::class.java)
                        intentExplicito.putExtra("id", idItem)
                        //contexto.startActivityForResult(intentExplicito,401)
                        contexto.startActivity(intentExplicito)
                        contexto.finish()
                        //  contexto.startActivity(Intent(contexto, MateriasFormularioActualizacion::class.java));
                        Toast.makeText(contexto, "Editar clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    //Eliminar
                    R.id.menuEliminarMaterias -> {
                        val builder = AlertDialog.Builder(contexto)
                        builder.setTitle("ALERTA !! \n Seguro quiere eliminar esta materia?")
                        builder.setPositiveButton(
                            "Aceptar",
                            DialogInterface.OnClickListener { dialog, which ->
                                BaseDeDatos.TablaMateria!!.eliminarMateriaPorId(idItem.id)
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
                        )

                        builder.setNegativeButton(
                            "Cancelar",null
                        )

                        val dialog = builder.create()
                        dialog.show()

                        //BaseDeDatos.TablaMateria!!.eliminarMateriaPorCodigo(idItem.codigo.toString())

                        //contexto.setResult(Activity.RESULT_OK)
                        //contexto.startActivityForResult(Intent(contexto,MateriasActivity::class.java),400)


                        true
                    }

                    //Lista Estudiantes
                    R.id.menuListaEstudiantes -> {
                        var listaEstudiante = arrayListOf<Estudiante>()
                        BaseDeDatos.TablaEstudiante = SQLiteHelper(contexto)
                        if (BaseDeDatos.TablaEstudiante != null) {
                            listaEstudiante =
                                BaseDeDatos.TablaEstudiante!!.consultarEstudiantePorId(idItem.id)
                        }

                        iniciarRecyclerView(listaEstudiante, EstudiantesActivity(), recyclerView)
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

    fun iniciarRecyclerView(
        lista: ArrayList<Estudiante>,
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