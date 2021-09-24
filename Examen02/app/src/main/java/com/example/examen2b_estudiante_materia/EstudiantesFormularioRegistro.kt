package com.example.examen2b_estudiante_materia

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EstudiantesFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_registro)

        //Botón para crear
        val btnCrearEstudiante = findViewById<Button>(R.id.btnCrearEstudiante)

        btnCrearEstudiante.setOnClickListener {
            crearEstudiante()
        }
    }

    private fun crearEstudiante() {
        //Ingreso de datos

        val editTextCarrera = findViewById<EditText>(R.id.edtxtCarreraEstudiante)
        val editTextCedula = findViewById<EditText>(R.id.edtxtCedulaEstudiante)
        val editTextCodigoUnico = findViewById<EditText>(R.id.edtxtCodigoEstudiante)
        val checkBoxEstadoEstudiante = findViewById<CheckBox>(R.id.checkBoxEstadoEstudiante)
        val editTextFechaNacimiento = findViewById<EditText>(R.id.edtxtfechaNacimientoEstudiante)
        val editTexIdMateria = findViewById<EditText>(R.id.edtxtIDMateriaEstudiante)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreEstudiante)


        val db = Firebase.firestore
        val referenciaMateria = db.collection("materia")
        referenciaMateria
            .whereEqualTo(
            "codigo",
            editTexIdMateria.text.toString()
        )
            .get()
            .addOnSuccessListener {
                for (documentos in it){
                    Log.i(" bdd","${documentos.data}")
                    Log.i(" bdd","${documentos.id}")

                    val nuevoEstudiante = hashMapOf<String, Any>(
                        "carrera" to editTextCarrera.text.toString(),
                        "cedula" to editTextCedula.text.toString(),
                        "estado" to checkBoxEstadoEstudiante.isChecked,
                        "fecha" to editTextFechaNacimiento.text.toString(),
                        "materia" to documentos.id,
                        "nombre" to editTextNombre.text.toString(),
                        "numeroUnico" to editTextCodigoUnico.text.toString(),
                        //"latitud"
                        //"longitud"
                    )


                    val referenciaEstudiante = db.collection("estudiante")
                    referenciaEstudiante.add(nuevoEstudiante).addOnSuccessListener {
                        editTextCarrera.text.clear()
                        editTextCedula.text.clear()
                        editTextCodigoUnico.text.clear()
                        editTextFechaNacimiento.text.clear()
                        editTexIdMateria.text.clear()
                        editTextNombre.text.clear()
                        Toast.makeText(
                            this,
                            "Estudiante ingresada con éxito",
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
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)

            }


    }
}