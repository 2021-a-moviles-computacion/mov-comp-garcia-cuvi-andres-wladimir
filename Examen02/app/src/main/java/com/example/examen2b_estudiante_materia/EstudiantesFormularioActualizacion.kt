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
        val ciudadades = arrayOf(
            "Cuenca", "Quito", "Guayaquil", "Machala", "Portoviejo", "Tulcán", "Loja",
            "Esmeraldas", "Guaranda", "Tena", "Puyo", "Macas", "Latacunga"
        )

        val latitudes = arrayOf(
            -2.899983604102197, //Cuenca
            -0.16708802339901171,     //Quito
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