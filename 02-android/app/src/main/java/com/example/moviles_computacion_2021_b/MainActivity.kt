package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIrACicloVida = findViewById<Button>(
            R.id.btnCicloVida
        )

        btnIrACicloVida.setOnClickListener{
            abrirCicloVida()
        }

    }

    fun abrirCicloVida(){
        val intentExplicito = Intent(
            this,
            ACicloVida::class.java
        )
        this.startActivity(intentExplicito);
    }
}