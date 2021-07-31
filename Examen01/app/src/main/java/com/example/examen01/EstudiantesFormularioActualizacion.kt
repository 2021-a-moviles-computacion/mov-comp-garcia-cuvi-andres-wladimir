package com.example.examen01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class EstudiantesFormularioActualizacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_actualizacion)

        val itemId = intent.getParcelableExtra<Estudiante>("id")
        Log.i("bdd", "${itemId?.id}")

        BaseDeDatos.TablaEstudiante = SQLiteHelper(this)
        BaseDeDatos.TablaMateria = SQLiteHelper(this)
        var materiaEncontrada = Materia(0, "", "", 0, "", true)
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

        editTextCodigoUnico.setText(itemId!!.numeroUnico)
        editTextCedula.setText(itemId!!.cedula)
        editTextNombre.setText(itemId!!.nombre)
        editTextCarrera.setText(itemId!!.carrera)
        editTextFechaNacimiento.setText(itemId!!.fechaNacimiento)

        if (BaseDeDatos.TablaMateria != null) {
            materiaEncontrada =
                BaseDeDatos.TablaMateria!!.consultarMateriaPorId(itemId!!.idMateria)
        }

        editTexIdMateria.setText( materiaEncontrada.codigo.toString())

//        Boton para Actualizar

        val btnActualizarEstudiante = findViewById<Button>(R.id.btnActualizarEstudiante)

        btnActualizarEstudiante.setOnClickListener {

            if (BaseDeDatos.TablaEstudiante != null) {
                BaseDeDatos.TablaEstudiante!!.actualizarEstudianteFormulario(
                    itemId!!.id,
                    materiaEncontrada.id,
                    editTextCodigoUnico.text.toString(),
                    editTextCedula.text.toString(),
                    editTextNombre.text.toString(),
                    editTextCarrera.text.toString(),
                    editTextFechaNacimiento.text.toString(),
                    checkBoxEstadoEstudiante.isChecked
                )

                Toast.makeText(this, "Estudiante actualizado exitosamente", Toast.LENGTH_SHORT)
                    .show()
                Log.i("bdd", "Estudiante actualizada")
                this.finish()

            }
        }


    }
}