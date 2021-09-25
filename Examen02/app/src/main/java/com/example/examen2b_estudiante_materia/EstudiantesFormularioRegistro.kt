package com.example.examen2b_estudiante_materia

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EstudiantesFormularioRegistro : AppCompatActivity() {
    var latitud = 0.0
    var longitud =0.0
    var ciudadSeleccionado = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_registro)

        //ciudades
        val ciudadades = arrayOf("Amsterdam", "Berlin", "Bogotá", "London", "Madrid", "México DC", "New York",
            "Paris", "Quito", "Rio de Janeiro", "Tokyo", "Vienna", "Zürich")

        val latitudes = arrayOf(52.35573356081986, 52.5192880232342, 4.708507195958197, 51.509162838374365, 40.41800636913152, 19.432671298400965,
            40.73378562762449, 48.85901815928653, -0.21922483476220822,-22.938658330236088,35.68614253218143,48.217399805696736,47.36894099516527)
        val longitudes = arrayOf(4.881766688070693, 13.409614318972272, -74.05425716885155, -0.12767985390655445, -3.7077094446139935, -99.12723505493796,
            -73.99320893340935,2.296522110492033, -78.51152304364815, -43.228146943991, 139.78434424093086, 16.399427792363813, 8.550536094895179)


        val spinnerUbicacion = findViewById<Spinner>(R.id.spUbicacionEstudiante)

        val adaptadorUbicacion = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            ciudadades
        )

        spinnerUbicacion.adapter = adaptadorUbicacion

        spinnerUbicacion.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                ciudadSeleccionado = parent?.getItemAtPosition(position) as String
                latitud=latitudes[position]
                longitud=longitudes[position]
                Log.i("ciudades", "$position")
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }


        }


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
                        "latitud" to latitud,
                        "longitud" to longitud
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