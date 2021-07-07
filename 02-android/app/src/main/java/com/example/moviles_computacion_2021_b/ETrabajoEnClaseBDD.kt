package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class ETrabajoEnClaseBDD : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trabajo_en_clase_bdd)
        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)

        //  ingreso de datos
        val editTextNombre = findViewById<EditText>(
            R.id.edtxtNombre
        )
        val editTextDescripcion = findViewById<EditText>(
            R.id.edtxtDescrip
        )

        val editTextId = findViewById<EditText>(
            R.id.edtxtId
        )

        //     Botón para usar el botón crear
        val btnCrearUsuario = findViewById<Button>(
            R.id.btnCrearUsuario
        )

        btnCrearUsuario.setOnClickListener {
            if (EBaseDeDatos.TablaUsuario != null) {
                EBaseDeDatos.TablaUsuario!!.crearUsuarioFormulario(
                    editTextNombre.text.toString(),
                    editTextDescripcion.text.toString()
                )
                Log.i("bdd", "Usuario creado")
            }
        }

//       Botón  para usar la consulta por id

        val btnConsultarUsuario = findViewById<Button>(
            R.id.btnConsultarUsuario
        )

        btnConsultarUsuario.setOnClickListener {
            if (EBaseDeDatos.TablaUsuario != null) {
                val usuario = EBaseDeDatos.TablaUsuario!!.consultarUsuarioPorId(
                    editTextId.text.toString().toInt()
                )

                Log.i(
                    "bdd", "Usuario encontrado: ${editTextId.text} " +
                            "es el usuario -> ${usuario.nombre} " +
                            "con su descripción : ${usuario.descripcion}"
                )
            }
        }

        //       Botón  para usar la actualización

        val btnActualizarUsuario = findViewById<Button>(
            R.id.btnActualizarUsuario
        )

        btnActualizarUsuario.setOnClickListener {
            if (EBaseDeDatos.TablaUsuario != null) {
                val actualizado = EBaseDeDatos.TablaUsuario!!.actualizarUsuarioFormulario(
                    editTextNombre.text.toString(),
                    editTextDescripcion.text.toString(),
                    editTextId.text.toString().toInt()
                )
                Log.i("bdd", "usuario id: ${editTextId.text} estado actualizacion ${actualizado} ")

            }
            Log.i("bdd", "pasó XD")
        }

        //       Botón  para usar la eliminar

        val btnEliminarrUsuario = findViewById<Button>(
            R.id.btnEliminarUsuario
        )

        btnEliminarrUsuario.setOnClickListener {
            if (EBaseDeDatos.TablaUsuario != null) {
                val eliminado = EBaseDeDatos.TablaUsuario!!.eliminarUsuarioFormulario(
                    editTextId.text.toString().toInt()
                )
                Log.i("bdd", "usuario id: ${editTextId.text} estado eliminacion ${eliminado} ")
            }
       //     Log.i("bdd", "pasó XD")
        }

    }
}