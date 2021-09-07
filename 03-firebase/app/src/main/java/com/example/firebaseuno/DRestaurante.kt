package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestaurante)

        val botonCrearRestaurant = findViewById<Button>(R.id.btnCrearRestaurante)
        botonCrearRestaurant.setOnClickListener {
            crearRestaurante()
        }

    }

    private fun crearRestaurante() {
        val editTextNombre = findViewById<EditText>(R.id.etNombreRestaurante)
        val nuevoRestaurante = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString()
        )

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")
        referencia
            .add(nuevoRestaurante)
            .addOnSuccessListener {
                editTextNombre.text.clear()
                Toast.makeText(
                    this,
                    "Ingreso exitoso",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "No se puedo completar el ingreso",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}