package com.example.deber02.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02.*
import com.example.deber02.databinding.FragmentChatsBinding
import com.example.deber02.databinding.FragmentLlamadasBinding

class FragmentLlamadas: Fragment() {

    private var _binding: FragmentLlamadasBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentLlamadasBinding.inflate(inflater,container,false)
        val root = binding.root

        var listaUsuarioLlamada = arrayListOf<UsuarioLlamada>()
        val recyclerViewLlamada = binding.rvLlamadasFragment


        listaUsuarioLlamada.add(UsuarioLlamada("Jaime Roldos","Hace 50 minutos",R.drawable.fake,R.drawable.flecha_enviar))

        listaUsuarioLlamada.add(UsuarioLlamada("Andrés García","Ayer 23:21",R.drawable.andres,R.drawable.flecha_perdida))

        listaUsuarioLlamada.add(UsuarioLlamada("Pedro Pérez","Ayer 11:54",R.drawable.user,R.drawable.flecha_recibir))

        listaUsuarioLlamada.add(UsuarioLlamada("Jose Guarderas","Ayer 09:50",R.drawable.jose,R.drawable.flecha_recibir))

        listaUsuarioLlamada.add(UsuarioLlamada("Edison","16 de agosto 13:46",R.drawable.edi,R.drawable.flecha_perdida))


        listaUsuarioLlamada.add(UsuarioLlamada("Daniel","16 de agosto 11:43",R.drawable.daniel,R.drawable.flecha_enviar))

        listaUsuarioLlamada.add(UsuarioLlamada("Erick","15 de agosto 20:04",R.drawable.fotos2,R.drawable.flecha_enviar))

        listaUsuarioLlamada.add(UsuarioLlamada("Cesar Taco","15 de agosto 14:40",R.drawable.cesi,R.drawable.flecha_perdida))




        iniciarRecyclerView(listaUsuarioLlamada, MainActivity(),recyclerViewLlamada)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun iniciarRecyclerView(
        lista: ArrayList<UsuarioLlamada>,
        activity: MainActivity,
        recyclerView: RecyclerView

    ){
        val adaptador = AdaptadorUsuarioLlamada(
            activity,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()
    }

}