package com.example.examen2b_estudiante_materia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.examen2b_estudiante_materia.dto.MateriaDto
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MateriasFormularioActualizacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias_formulario_actualizacion)

        val itemId = intent.getParcelableExtra<MateriaDto>("id")
        //val itemId = intent.getSerializableExtra("id") as? MateriaDto
        //val itemIdUid = intent.extras
        //val uid = intent.getStringExtra("uid").toString()
        //Log.i("bdd", uid)
        Log.i("bdd", "${itemId?.uid}")

        val db = Firebase.firestore
        val referenciaMateria = db.collection("materia")

        //Ingreso de datos

        val editTextCodigo = findViewById<EditText>(R.id.edtxtCodigoMateriaActualizar)
        val editTextNombre = findViewById<EditText>(R.id.edtxtNombreMateriactualizar)
        val editTextCreditos = findViewById<EditText>(R.id.edtxtCreditosMateriactualizar)
        val editTextAula = findViewById<EditText>(R.id.edtxtAulaMateriactualizar)
        val checkBoxEstado = findViewById<CheckBox>(R.id.checkBoxEstadoMateriactualizar)


        editTextCodigo.setText(itemId!!.codigo)
        editTextNombre.setText(itemId.nombre)
        editTextCreditos.setText(itemId.creditos.toString())
        editTextAula.setText(itemId.aula)

        //Boton para Actualizar
        val btnActualizarMateria = findViewById<Button>(R.id.btnActualizarMateria)

        btnActualizarMateria.setOnClickListener {


            if (referenciaMateria != null) {
                referenciaMateria.document(itemId.uid.toString()).update(
                    "codigo",editTextCodigo.text.toString(),
                    "nombre",editTextNombre.text.toString(),
                    "creditos", editTextCreditos.text.toString().toInt(),
                    "aula", editTextAula.text.toString(),
                    "estado",checkBoxEstado.isChecked
                )
                 abrirActividad(MateriasActivity::class.java)
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