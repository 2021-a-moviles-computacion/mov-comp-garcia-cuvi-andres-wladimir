package com.example.examen2b_estudiante_materia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //     Botón para ir a la Actividad MateriasActivity

        val btnIrMateriasActivity = findViewById<Button>(
            R.id.btnIrAMaterias
        )

        btnIrMateriasActivity.setOnClickListener{
            abrirActividad(MateriasActivity::class.java)
        }

        //     Botón para ir a la Actividad EstudiantesActivity

        val btnIrEstudiantesActivity = findViewById<Button>(
            R.id.btnIrAEstudiantes
        )

        btnIrEstudiantesActivity.setOnClickListener{
            abrirActividad(EstudiantesActivity::class.java)
        }


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
}