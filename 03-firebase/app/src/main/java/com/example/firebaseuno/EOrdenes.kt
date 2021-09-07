package com.example.firebaseuno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.firebaseuno.dto.FirestoreOrdenDto
import com.example.firebaseuno.dto.FirestoreProductoDto
import com.example.firebaseuno.dto.FirestoreRestauranteDto
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {

    var arregloRestauranteDto = ArrayList<FirestoreRestauranteDto>()
    var arregloProductoDto = ArrayList<FirestoreProductoDto>()
    var productoSeleccionado = FirestoreProductoDto()
    var restauranteSeleccionado = FirestoreRestauranteDto()
    var arregloOrdenDto = ArrayList<FirestoreOrdenDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)


        var registroRestaurante: (MutableList<DocumentSnapshot>)
        var registroProducto: (MutableList<DocumentSnapshot>)


        var listaRestaurante = ArrayList<FirestoreRestauranteDto>()
        var listaProducto = ArrayList<FirestoreProductoDto>()


        val db = Firebase.firestore

        val referenciaProducto = db.collection("producto")
        val referenciaRestaurante = db.collection("restaurante")

        referenciaRestaurante
            .get()
            .addOnSuccessListener {
                registroRestaurante = it.documents
                registroRestaurante.forEach { iteracion ->

                    var objRestauranteDto = iteracion.toObject(FirestoreRestauranteDto::class.java)
                    objRestauranteDto!!.uid = iteracion.id

                    listaRestaurante.add(objRestauranteDto)
                }

                arregloRestauranteDto = listaRestaurante


                referenciaProducto
                    .get()
                    .addOnSuccessListener {
                        registroProducto = it.documents
                        registroProducto.forEach { iteracion ->
                            var objProductoDto =
                                iteracion.toObject(FirestoreProductoDto::class.java)
                            objProductoDto!!.uid = iteracion.id
                            objProductoDto!!.nombre = iteracion.get("nombre").toString()
                            objProductoDto!!.precio = iteracion.get("precio").toString().toDouble()

                            listaProducto.add(objProductoDto)
                        }
                        arregloProductoDto = listaProducto
                        val spinnerRestaurantes = findViewById<Spinner>(R.id.sprestaurantes)
                        val spinnerProductos = findViewById<Spinner>(R.id.spProducto)

                        val adaptadorRestaurante = ArrayAdapter(
                            this,
                            android.R.layout.simple_spinner_dropdown_item,
                            arregloRestauranteDto
                        )
                        spinnerRestaurantes.adapter = adaptadorRestaurante


                        val adaptadorProducto = ArrayAdapter(
                            this,
                            android.R.layout.simple_spinner_dropdown_item,
                            arregloProductoDto
                        )
                        spinnerProductos.adapter = adaptadorProducto

                    }


            }


        val spinnerRestaurantes = findViewById<Spinner>(R.id.sprestaurantes)
        val spinnerProductos = findViewById<Spinner>(R.id.spProducto)

        spinnerRestaurantes.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                restauranteSeleccionado = arregloRestauranteDto[position]

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

        spinnerProductos.onItemSelectedListener = object :
            AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                productoSeleccionado = arregloProductoDto[position]

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

        val editTextCantidadProducto = findViewById<EditText>(R.id.etCantidadProductos)
        val botonAdd = findViewById<Button>(R.id.btnAñadirListaProductos)

        val listViewProductos = findViewById<ListView>(R.id.lvListaPedido)
        val listViewProductosAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_selectable_list_item,
            arregloOrdenDto
        )

        listViewProductos.adapter = listViewProductosAdapter

        botonAdd.setOnClickListener {
            var editTextCantidadProductoCambio: String = editTextCantidadProducto.text.toString()
            if (editTextCantidadProductoCambio == ""){
                editTextCantidadProductoCambio = "1"
            }

            val orden = FirestoreOrdenDto(
                productoSeleccionado.nombre,
                productoSeleccionado.precio!!,
                editTextCantidadProductoCambio.toInt()
            )

            llenarListView(orden,listViewProductosAdapter)

        }

    }

    private fun llenarListView(orden: FirestoreOrdenDto, listViewProductosAdapter: ArrayAdapter<FirestoreOrdenDto>) {

        val textViewTotalAPagar = findViewById<TextView>(R.id.tvTotalAPagar)

        arregloOrdenDto.add(orden)
        listViewProductosAdapter.notifyDataSetChanged()
        textViewTotalAPagar.text=(
                textViewTotalAPagar.text.toString().toDouble() + orden.CalcularTotalProductos()
                ).toString()

    }


}

