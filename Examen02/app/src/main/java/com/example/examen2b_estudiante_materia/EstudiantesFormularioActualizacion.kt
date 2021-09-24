package com.example.examen2b_estudiante_materia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.examen2b_estudiante_materia.dto.EstudianteDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EstudiantesFormularioActualizacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_actualizacion)

        val itemId = intent.getParcelableExtra<EstudianteDto>("id")

        Log.i("bdd", "${itemId?.uid}")
        Log.i("bdd", "${itemId?.numeroUnico}")

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