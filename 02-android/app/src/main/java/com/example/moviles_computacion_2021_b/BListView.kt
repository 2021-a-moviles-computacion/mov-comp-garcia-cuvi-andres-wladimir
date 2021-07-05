package com.example.moviles_computacion_2021_b

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    var posicionItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val arregloNumeros = BBaseDatosMemoria.arregloBEntrenador

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,    //Layout precargado por Android (visual)
            arregloNumeros
        )


        val listViewEjemplo = findViewById<ListView>(R.id.ltvEjemplo)
        listViewEjemplo.adapter = adaptador

        val btnAnadirNumero = findViewById<Button>(R.id.btnAnadirNumero)
        btnAnadirNumero.setOnClickListener {
            anadirItemsAListView(
                BEntrenador("Prueba", "d@d.com"),
                arregloNumeros,
                adaptador
            )
        }


        listViewEjemplo
            .setOnItemClickListener { adapterView, view, posicion, id ->
                Log.i("list-view", "Dio click ${posicion}")
            }
        listViewEjemplo
            .setOnItemLongClickListener { adapterView, view, posicion, id ->
                Log.i("list-view", "Dio click ${posicion}")
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Titulo")
//                builder.setMessage("Mensaje")

                val seleccionUsuario = booleanArrayOf(
                    true,
                    false,
                    false
                )

                val opciones = resources.getStringArray(R.array.string_array_opciones_dialogo)
                builder.setMultiChoiceItems(
                    opciones,
                    seleccionUsuario,
                    {
                        dialog, wich, isChecked ->
                        Log.i("list-view" ,"${wich} ${isChecked}")
                    }
                )

                builder.setPositiveButton(
                    "Si",
                    DialogInterface.OnClickListener { dialog, which ->
                        Log.i("list-vire", "Sí")
                    }
                )
                builder.setNegativeButton(
                    "No",
                    null
                )
                val dialogo = builder.create()
                dialogo.show()

                return@setOnItemLongClickListener true
            }

//        registerForCOnextMenu(listViewEjemplo)


    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        posicionItemSeleccionado = id
        Log.i("list-view", "List view ${posicionItemSeleccionado}")
        Log.i("list-view", "Entrenador ${BBaseDatosMemoria.arregloBEntrenador[id]}")
    }


    fun anadirItemsAListView(
        valor: BEntrenador,
        arreglo: ArrayList<BEntrenador>,
        adaptador: ArrayAdapter<BEntrenador>
    ) {
        arreglo.add(valor)
        adaptador.notifyDataSetChanged()        //Actualiza la interfaz
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            //Editar
            R.id.miEditar -> {
                Log.i(
                    "list-view", "Editar ${
                        BBaseDatosMemoria.arregloBEntrenador[
                                posicionItemSeleccionado
                        ]
                    }"
                )
                return true
            }
            //Eliminar
            R.id.miEliminar -> {
                Log.i(
                    "list-view", "Eliminar ${
                        BBaseDatosMemoria.arregloBEntrenador[
                                posicionItemSeleccionado
                        ]
                    }"
                )
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}