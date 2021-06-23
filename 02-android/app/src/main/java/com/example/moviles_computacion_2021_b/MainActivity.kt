package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//     Botón para ir a la Actividad ACicloVida
        val btnIrACicloVida = findViewById<Button>(
            R.id.btnCicloVida
        )

        btnIrACicloVida.setOnClickListener{
            abrirActividad(ACicloVida::class.java)
        }

//     Botón para ir a la Actividad ACicloVida

        val btnIrBListView = findViewById<Button>(
            R.id.btnIrListView
        )

        btnIrBListView.setOnClickListener{
            abrirActividad(BListView::class.java)
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