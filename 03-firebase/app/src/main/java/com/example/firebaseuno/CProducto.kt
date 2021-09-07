package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cproducto)

        val botonCrearProducto = findViewById<Button>(R.id.btnCrearProducto)
        botonCrearProducto.setOnClickListener {
            crearProducto()
        }
    }

    private fun crearProducto() {
        val editTextNombre = findViewById<EditText>(R.id.etNombreProducto)
        val editTextPrecio = findViewById<EditText>(R.id.etPrecioProducto)
        val nuevoProducto = hashMapOf<String,Any>(
            "nombre" to editTextNombre.text.toString(),
            "precio" to editTextPrecio.text.toString().toDouble()
        )
        val db = Firebase.firestore
        val referencia = db.collection("producto")
        referencia.add(nuevoProducto).addOnSuccessListener {
            editTextNombre.text.clear()
            editTextPrecio.text.clear()
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