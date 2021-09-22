package com.example.examen2b_estudiante_materia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MateriasFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias_formulario_registro)

        //Boton para crear
        val btnCrearMateria = findViewById<Button>(R.id.btnCrearMateria)

        btnCrearMateria.setOnClickListener {
            crearMateria()
        }

    }

    private fun crearMateria(){
        //Ingreso de datos

        val editTextCodigo = findViewById<EditText>(R.id.edtxtCodigoMateria)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreMateria)
        val editTextCreditos = findViewById<EditText>(R.id.edtxtCreditosMateria)
        val editTextAula = findViewById<EditText>(R.id.edtxtAulaMateria)
        val checkBoxEstado = findViewById<CheckBox>(R.id.checkBoxEstadoMateria)

        val nuevaMateria = hashMapOf<String,Any>(
            "codigo" to editTextCodigo.text.toString(),
            "nombre"  to editTextNombre.text.toString(),
            "creditos" to editTextCreditos.text.toString().toInt(),
            "aula" to editTextAula.text.toString(),
            "estado" to checkBoxEstado.isChecked
        )

        val db = Firebase.firestore
        val referencia = db.collection("materia")
        referencia.add(nuevaMateria).addOnSuccessListener {
            editTextCodigo.text.clear()
            editTextNombre.text.clear()
            editTextCreditos.text.clear()
            editTextAula.text.clear()
            Toast.makeText(
                this,
                "Materia ingresada con éxito",
                Toast.LENGTH_SHORT
            ).show()
            this.finish()
        }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "No se puedo completar el registro",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}