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
    var longitud = 0.0
    var ciudadSeleccionado = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_registro)

        //ciudades
        val ciudadades = arrayOf(
            "Cuenca", "Quito", "Guayaquil", "Machala", "Portoviejo", "Tulcán", "Loja",
            "Esmeraldas", "Guaranda", "Tena", "Puyo", "Macas", "Latacunga"
        )

        val latitudes = arrayOf(
            -2.899983604102197, //Cuenca
            -0.16708802339901171,   //Quito
            -2.1586702581784105,    //Guayaquil
            -3.2599438023758105,     //Machala
            -1.0593594129073896,   //Portoviejo
            0.815415273853226,  //Tulcán
            -4.007384829569846,   //Loja
            0.9744099399633774,  //Esmeraldas
            -1.590284192441808,    //Guaranda
            -0.9923247385890627,     //Tena
            -1.4854935397624056,  //Puyo
            -2.3035914769514743, //Macas
            -0.932021412328821   //Latacunga
        )

        val longitudes = arrayOf(
            -79.00723364987584,//Cuenca
            -78.48597486258072, //Quito
            -79.91488728656917,//Guayaquil
            -79.95450717452121,//Machala
            -80.471475794403,//Portoviejo
            -77.7177583445208,//Tulcán
            -79.21278681284306, //Loja
            -79.65472087897591,//Esmeraldas
            -79.0017127680853, //Guaranda
            -77.81624816815025,//Tena
            -78.00631665813665, //Puyo
            -78.11622687541667,//Macas
            -78.61491339994664//Latacunga
        )



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
                latitud = latitudes[position]
                longitud = longitudes[position]
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
                for (documentos in it) {
                    Log.i(" bdd", "${documentos.data}")
                    Log.i(" bdd", "${documentos.id}")

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