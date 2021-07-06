package com.example.moviles_computacion_2021_b

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class ETrabajoEnClaseBDD : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trabajo_en_clase_bdd)

        EBaseDeDatos.TablaUsuario = ESqliteHelperUsuario(this)
        var db: ESqliteHelperUsuario = ESqliteHelperUsuario(this)
        db.writableDatabase
        //     Botón para usar el botón crear
        val btnCrearUsuario = findViewById<Button>(
            R.id.btnCrearUsuario
        )
        if(db!= null){

            btnCrearUsuario.setOnClickListener{

                Log.i("bdd", "Funcó el botón")
                db.crearUsuarioFormulario("Andres",
                    "Estudiante")
                Log.i("bdd", "Usuario creado")

                ;

            }
        }


        Log.i("bdd","pasó XD")


    }


}