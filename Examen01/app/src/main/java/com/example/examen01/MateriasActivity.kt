package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView

class MateriasActivity : AppCompatActivity() {

    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        var listaMateria = arrayListOf<Materia>()

        BaseDeDatos.TablaMateria = SQLiteHelper(this)
        if (BaseDeDatos.TablaMateria != null) {
            listaMateria = BaseDeDatos.TablaMateria!!.consultaMateriaTotal()
        }



      /*  listaMateria.add(Materia("ICCR201","Arquitectura de computadores",6,"B1",true))
        listaMateria.add(Materia("ICCR733","Aplicaciones Web",6,"A1",true))*/

        val recyclerViewMateria = findViewById<RecyclerView>(R.id.rvListaMaterias)

        //Iniciar Recycler View
        iniciarRecyclerView(listaMateria,this,recyclerViewMateria)

        //     Botón para ir al Formulario de Registro de Materias
        val btnIrAFormularioRegistroMaterias = findViewById<Button>(
            R.id.btnInsertarMaterias
        )

        btnIrAFormularioRegistroMaterias.setOnClickListener{
            abrirActividad(MateriasFormularioRegistro::class.java)
        }

        //Contextual Menu
//        val linearLayoutId: LinearLayoutCompat
//        linearLayoutId = view.findViewById(R.id.LinearLayoutIdMaterias)
            registerForContextMenu(recyclerViewMateria)


    }

    fun iniciarRecyclerView(
        lista: ArrayList<Materia>,
        activity: MateriasActivity,
        recyclerView: RecyclerView
    ){
        val adaptador = AdapterMaterias(
            activity,
           // this,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

        //registerForContextMenu(recyclerView)



    }


    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito);
    }

/*    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_materias,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position

        posicionItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        BaseDeDatos.TablaMateria = SQLiteHelper(this)
        var idItem = BaseDeDatos.TablaMateria!!.consultaMateriaTotal()[posicionItemSeleccionado]
        return when (item?.itemId){
            //Editar
            R.id.menuEditarMaterias -> {

                return true
            }

            //Eliminar
            R.id.menuEliminarMaterias -> {
              //  BaseDeDatos.TablaMateria!!.eliminarMateriaPorId(idItem.toString().toInt())

                return true
            }

            R.id.menuListaEstudiantes -> {

                return true
            }
            else -> super.onContextItemSelected(item)

        }

    }*/


}















