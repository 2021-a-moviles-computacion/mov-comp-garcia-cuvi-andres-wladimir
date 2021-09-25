package com.example.examen2b_estudiante_materia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.examen2b_estudiante_materia.dto.EstudianteDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EstudiantesFormularioActualizacion : AppCompatActivity() {
    var latitud = 0.0
    var longitud =0.0
    var ciudadSeleccionado = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_actualizacion)



        val itemId = intent.getParcelableExtra<EstudianteDto>("id")

        Log.i("bdd", "${itemId?.uid}")
        Log.i("bdd", "${itemId?.numeroUnico}")


        //ciudades
        val ciudadades = arrayOf("Amsterdam", "Berlin", "Bogotá", "London", "Madrid", "México DC", "New York",
            "Paris", "Quito", "Rio de Janeiro", "Tokyo", "Vienna", "Zürich")

        val latitudes = arrayOf(52.35573356081986, 52.5192880232342, 4.708507195958197, 51.509162838374365, 40.41800636913152, 19.432671298400965,
            40.73378562762449, 48.85901815928653, -0.21922483476220822,-22.938658330236088,35.68614253218143,48.217399805696736,47.36894099516527)
        val longitudes = arrayOf(4.881766688070693, 13.409614318972272, -74.05425716885155, -0.12767985390655445, -3.7077094446139935, -99.12723505493796,
            -73.99320893340935,2.296522110492033, -78.51152304364815, -43.228146943991, 139.78434424093086, 16.399427792363813, 8.550536094895179)

        val spinnerUbicacion = findViewById<Spinner>(R.id.spUbicacionEstudianteActualizar)

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

        val db = Firebase.firestore
        val referenciaMateria = db.collection("materia")
        val referenciaEstudiante = db.collection("estudiante")


        //Ingreso de datos
        val editTextCodigoUnico = findViewById<EditText>(R.id.edtxtCodigoEstudianteActualizar)
        val editTextCedula = findViewById<EditText>(R.id.edtxtCedulaEstudianteActualizar)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreEstudianteActualizar)
        val editTextCarrera = findViewById<EditText>(R.id.edtxtCarreraEstudianteActualizar)
        val editTextFechaNacimiento =
            findViewById<EditText>(R.id.edtxtfechaNacimientoEstudianteActualizar)
        val checkBoxEstadoEstudiante =
            findViewById<CheckBox>(R.id.checkBoxEstadoEstudianteActualizar)
        val editTexIdMateria = findViewById<EditText>(R.id.edtxtIDMateriaEstudianteActualizar)
        var materiaUid =""
        editTextCodigoUnico.setText(itemId!!.numeroUnico)
        editTextCedula.setText(itemId.cedula)
        editTextNombre.setText(itemId.nombre)
        editTextCarrera.setText(itemId.carrera)
        editTextFechaNacimiento.setText(itemId.fechaNacimiento)

        referenciaMateria
            .document(itemId.idMateria.toString())
            .get()
            .addOnSuccessListener {document->
                editTexIdMateria.setText(document.data?.get("codigo").toString())
                materiaUid = document.id
            }

        //        Boton para Actualizar

        val btnActualizarEstudiante = findViewById<Button>(R.id.btnActualizarEstudiante)

        btnActualizarEstudiante.setOnClickListener{
            if (referenciaEstudiante!=null){
                referenciaEstudiante.document(itemId.uid.toString()).update(
                    "carrera",editTextCarrera.text.toString(),
                    "cedula",editTextCedula.text.toString(),
                    "estado",checkBoxEstadoEstudiante.isChecked,
                    "fecha",editTextFechaNacimiento.text.toString(),
                    "materia",materiaUid,
                    "latitud",latitud,
                    "longitud",longitud,
                    "nombre",editTextNombre.text.toString(),
                    "numeroUnico",editTextCodigoUnico.text.toString(),
                )
                abrirActividad(EstudiantesActivity::class.java)
                Toast.makeText(this, "Estudiante actualizado exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Estudiante actualizado")
                this.finish()
            }

        }


    }


    fun abrirActividad(
        clase: Class<*>
    ) {
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito);
    }
}