package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.os.TestLooperManager
import android.view.inputmethod.ExtractedTextRequest
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {

    var totalLikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)

        val listaEntrenador = arrayListOf<BEntrenador>()
        val ligaPokemon = DLiga("Kanto","Liga Kanto")
        listaEntrenador.add(
            BEntrenador(
                "Andres",
                "1726264961",
                ligaPokemon
            )
        )

        listaEntrenador.add(
            BEntrenador(
                "Daniel",
                "17262649653",
                ligaPokemon
            )
        )


        val recyclerViewEntrenador = findViewById<RecyclerView>(
            R.id.rvEntrenador
        )

//        this.iniciarRecyclerView()
        iniciarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewEntrenador
        )
    }

    fun iniciarRecyclerView(
        lista: List<BEntrenador>,
        activity: GRecyclerView,
        recyclerView: RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            activity,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()
    }

    fun aumentarLikes(){
        totalLikes = totalLikes + 1
        val textView = findViewById<TextView>(R.id.tvTotalLikes)
        textView.text = totalLikes.toString()
    }
}