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