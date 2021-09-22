package com.example.firebaseuno

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {
    var ultimoDocumento: DocumentSnapshot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestaurante)

        val botonCrearRestaurant = findViewById<Button>(R.id.btnCrearRestaurante)
        botonCrearRestaurant.setOnClickListener {
            crearRestaurante()
        }

        val botonDatosPrueba = findViewById<Button>(R.id.btnDatosPrueba)

        botonDatosPrueba.setOnClickListener {
            crearDatosPrueba()
        }

        val botonConsultar = findViewById<Button>(R.id.btnConsultar)
        botonConsultar.setOnClickListener {
            consultar()
        }


    }

    private fun consultar() {
        val db = Firebase.firestore
        var citiesRef = db.collection("cities").limit(2)
       /* citiesRef
            .document("BJ")
            .get()
            .addOnSuccessListener {
                Log.i("consultas","${it.data}")
            }
            .addOnFailureListener {

            }

        citiesRef
            .whereEqualTo("country","China")
            .get()
            .addOnSuccessListener {
                Log.i("consultas","${it.documents}")
            }
            .addOnFailureListener {

            }*/

      /*  citiesRef
            .whereEqualTo("capital", false)
            .whereArrayContainsAny("regions", arrayListOf("social","norcal")).get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas","== array - contains ${ciudad.data}")
                }
            }*/

        /*citiesRef
            .whereEqualTo("capital", true)
            .whereGreaterThanOrEqualTo("population",1000000)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas","== array-contains ${ciudad.data}")
                }
            }*/

        //  Buscar por dos o más elementos campo '==' '<='

        /*      citiesRef
            .whereEqualTo("capital", false)
            .whereLessThanOrEqualTo("population",4000000)
            .orderBy("population", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
                for (ciudad in it){
                    Log.i("consultas","==array-contains ${ciudad.data}")
                }
            }*/

        if (ultimoDocumento != null){
            citiesRef = citiesRef.startAt(ultimoDocumento)
        }

        citiesRef
            .get()
            .addOnSuccessListener {
                ultimoDocumento = it.documents[it.size()-1]
                for (ciudad in it){
                    Log.i("consultas","${ciudad.data}")
                }
            }
            .addOnFailureListener {
                              

            }
    }

    private fun crearDatosPrueba() {
        val db = Firebase.firestore
        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    private fun crearRestaurante() {
        val editTextNombre = findViewById<EditText>(R.id.etNombreRestaurante)
        val nuevoRestaurante = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString()
        )

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")
        referencia
            .add(nuevoRestaurante)
            .addOnSuccessListener {
                editTextNombre.text.clear()
                Toast.makeText(
                    this,
                    "Ingreso exitoso",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "No se puedo completar el ingreso",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


}