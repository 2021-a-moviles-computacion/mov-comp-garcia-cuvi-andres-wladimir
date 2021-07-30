package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class EstudiantesFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes_formulario_registro)
        BaseDeDatos.TablaMateria = SQLiteHelper(this)
        BaseDeDatos.TablaEstudiante = SQLiteHelper(this)
        var materiaEncontrada = Materia(0, "", "", 0, "", true)

        //Ingreso de datos

        val editTextCodigoUnico = findViewById<EditText>(R.id.edtxtCodigoEstudiante)
        val editTextCedula = findViewById<EditText>(R.id.edtxtCedulaEstudiante)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreEstudiante)
        val editTextCarrera = findViewById<EditText>(R.id.edtxtCarreraEstudiante)
        val editTextFechaNacimiento = findViewById<EditText>(R.id.edtxtfechaNacimientoEstudiante)
        val editTexIdMateria = findViewById<EditText>(R.id.edtxtIDMateriaEstudiante)
        val checkBoxEstadoEstudiante = findViewById<CheckBox>(R.id.checkBoxEstadoEstudiante)



        //Botón para crear
        val btnCrearEstudiante = findViewById<Button>(R.id.btnCrearEstudiante)
        btnCrearEstudiante.setOnClickListener {
            val busquedaMateria: String = editTexIdMateria.text.toString()
            if (BaseDeDatos.TablaMateria != null) {
                materiaEncontrada =
                    BaseDeDatos.TablaMateria!!.consultarMateriaPorCodigo(busquedaMateria)
            }
            if (BaseDeDatos.TablaEstudiante != null) {
                BaseDeDatos.TablaEstudiante!!.crearEstudianteFormulario(
                    materiaEncontrada.id,
                    editTextCodigoUnico.text.toString(),
                    editTextCedula.text.toString(),
                    editTextNombre.text.toString(),
                    editTextCarrera.text.toString(),
                    editTextFechaNacimiento.text.toString(),
                    checkBoxEstadoEstudiante.isChecked
                )
                abrirActividad(EstudiantesActivity::class.java)
                val toast = Toast.makeText(this, "Estudiante creado exitosamente", Toast.LENGTH_SHORT).show()
                Log.i("bdd", "Estudiante creado")
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