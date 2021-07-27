package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MateriasFormularioActualizacion : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias_formulario_actualizacion)

        //val itemId = intent.getSerializableExtra("id") as? Materia
        val itemId = intent.getParcelableExtra<Materia>("id")

        Log.i("bdd", "${itemId?.id}")
        BaseDeDatos.TablaMateria = SQLiteHelper(this)

        //Ingreso de datos

        val editTextCodigo = findViewById<EditText>(R.id.edtxtCodigoMateriaActualizar)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreMateriactualizar)
        val editTextCreditos = findViewById<EditText>(R.id.edtxtCreditosMateriactualizar)
        val editTextAula = findViewById<EditText>(R.id.edtxtAulaMateriactualizar)
        val checkBoxEstado = findViewById<CheckBox>(R.id.checkBoxEstadoMateriactualizar)



        editTextCodigo.setText(itemId!!.codigo)
        editTextNombre.setText(itemId!!.nombre)
        editTextCreditos.setText(itemId!!.creditos.toString())
        editTextAula.setText(itemId!!.aula)


        //Boton para crear
        val btnActualizarMateria = findViewById<Button>(R.id.btnCrearMateriaActualizar)


        btnActualizarMateria.setOnClickListener {
            if (BaseDeDatos.TablaMateria != null) {
                BaseDeDatos.TablaMateria!!.actualizarMateriaFormulario(
                    itemId!!.id,
                    editTextCodigo.text.toString(),
                    editTextNombre.text.toString(),
                    editTextCreditos.text.toString().toInt(),
                    editTextAula.text.toString(),
                    checkBoxEstado.isChecked
                )

                abrirActividad(MateriasActivity::class.java)
                val toast =
                    Toast.makeText(this, "Materia actualizada exitosamente", Toast.LENGTH_SHORT)
                        .show()
                Log.i("bdd", "Materia actualizada")
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