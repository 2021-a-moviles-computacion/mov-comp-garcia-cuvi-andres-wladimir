package com.example.deber02.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02.*
import com.example.deber02.databinding.FragmentEstadosBinding

class FragmentEstados : Fragment() {

    private var _binding: FragmentEstadosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEstadosBinding.inflate(inflater, container, false)
        val root = binding.root

        var listaUsuarioEstado = arrayListOf<UsuarioEstado>()
        val recyclerViewEstado = binding.rvEstadosFragment

        listaUsuarioEstado.add(
            UsuarioEstado(
                "Andrés García",
                "hace 50 minutos",
                R.drawable.andres
            )
        )

        iniciarRecyclerView(listaUsuarioEstado, MainActivity(), recyclerViewEstado)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun iniciarRecyclerView(
        lista: ArrayList<UsuarioEstado>,
        activity: MainActivity,
        recyclerView: RecyclerView
    ) {
        val adaptador = AdaptadorUsuarioEstado(
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