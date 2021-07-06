package com.example.moviles_computacion_2021_b

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    val CODIGO_RESPUESTA_INTENT_IMPLICITO = 402

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//     Botón para ir a la Actividad ACicloVida
        val btnIrACicloVida = findViewById<Button>(
            R.id.btnCicloVida
        )

        btnIrACicloVida.setOnClickListener{
            abrirActividad(ACicloVida::class.java)
        }

//     Botón para ir a la Actividad ACicloVida

        val btnIrBListView = findViewById<Button>(
            R.id.btnIrListView
        )

        btnIrBListView.setOnClickListener{
            abrirActividad(BListView::class.java)
        }

//      Boton para ir a la Actividad CIntentExplicito con parámetros
        val btnIrIntent = findViewById<Button>(
            R.id.btnIrIntent
        )
        btnIrIntent
            .setOnClickListener{abrirActividadConParametros(CIntentExplicitoParametros::class.java)}

//      Boton para ir a la Actividad CIntenImplicito
        val btnIrintentImplicito = findViewById<Button>(R.id.btnIrIntentImplicito)

        btnIrintentImplicito.setOnClickListener{
            val intentConRespuesta = Intent(
                Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
            )
            startActivityForResult(intentConRespuesta, CODIGO_RESPUESTA_INTENT_IMPLICITO)
        }

//     Botón para ir a la Base de Datos
        val btntrabajoBDD = findViewById<Button>(
            R.id.btnTrabajoBDD
        )

        btntrabajoBDD.setOnClickListener{
            abrirActividad(ETrabajoEnClaseBDD::class.java)
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

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("nombre", "Andres")
        intentExplicito.putExtra("apellido", "Garcia")
        intentExplicito.putExtra("edad", 23)
        intentExplicito.putExtra("entrenador", BEntrenador("Andrés","Garcia"))
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_RESPUESTA_INTENT_EXPLICITO -> {
                if (resultCode == RESULT_OK){
                    Log.i("intent-explicito","SI actualización de datos")
                    if(data != null){
                        val nombre = data.getStringExtra("nombreModificado")
                        val edad = data.getIntExtra("edadModificado",0)
                        val entrenador = data.getParcelableExtra<BEntrenador>("entrenadorModificado")
                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")
                    }
                }
            }
            CODIGO_RESPUESTA_INTENT_IMPLICITO-> {
                if(resultCode == RESULT_OK ){
                    if(data != null){
                        if (data.data != null){
                            val cursor = contentResolver.query(
                                data.data!!,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("resultado","Telefono: ${telefono}")
                        }


                    }
                }
            }
        }
    }


}