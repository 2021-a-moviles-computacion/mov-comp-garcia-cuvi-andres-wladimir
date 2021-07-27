package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MateriasFormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias_formulario_registro)
        BaseDeDatos.TablaMateria = SQLiteHelper(this)
        
        //Ingreso de datos

        val editTextCodigo = findViewById<EditText>(R.id.edtxtCodigoMateria)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreMateria)
        val editTextCreditos = findViewById<EditText>(R.id.edtxtCreditosMateria)
        val editTextAula = findViewById<EditText>(R.id.edtxtAulaMateria)
        val checkBoxEstado = findViewById<CheckBox>(R.id.checkBoxEstadoMateria)

        //Boton para crear
        val btnCrearMateria = findViewById<Button>(R.id.btnCrearMateria)


        btnCrearMateria.setOnClickListener{
            if(BaseDeDatos.TablaMateria != null){
                BaseDeDatos.TablaMateria!!.crearMateriaFormulario(
                    editTextCodigo.text.toString(),
                    editTextNombre.text.toString(),
                    editTextCreditos.text.toString().toInt(),
                    editTextAula.text.toString(),
                    checkBoxEstado.isChecked
                )
                    abrirActividad(MateriasActivity::class.java)
                val toast = Toast.makeText(this, "Materia creada exitosamente", Toast.LENGTH_SHORT).show()
                Log.i("bdd", "Materia creada")
                this.finish()
            }
        }

    }


    fun abrirActividad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        this.startActivity(intentExplicito);
    }
}