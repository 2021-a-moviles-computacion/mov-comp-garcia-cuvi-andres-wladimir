package com.example.deber02.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02.AdaptadorUsuarioChat
import com.example.deber02.MainActivity
import com.example.deber02.R
import com.example.deber02.UsuarioChat
import com.example.deber02.databinding.FragmentChatsBinding


class FragmentChats : Fragment() {

    private var _binding: FragmentChatsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChatsBinding.inflate(inflater, container, false)
        val root = binding.root

        var listaUsuarioChat = arrayListOf<UsuarioChat>()
        val recyclerViewChats = binding.rvChatsFragment

        listaUsuarioChat.add(
            UsuarioChat(
                "Andres Garcia",
                "Hola como te ha ido compañero",
                R.drawable.andres,
                "20/04/21",
                4
            )
        )
        listaUsuarioChat.add(UsuarioChat("Jaime Roldos", "asomaff xd", R.drawable.fake, "10:58", 1))
        listaUsuarioChat.add(
            UsuarioChat(
                "Pedro Pérez",
                "Pasa el código :v que no me sale xd",
                R.drawable.user,
                "11:58",
                2
            )
        )

        listaUsuarioChat.add(
            UsuarioChat(
                "César Taco",
                "oe no hice el deber :c",
                R.drawable.cesi,
                "11:30",
                1
            )
        )

        listaUsuarioChat.add(
            UsuarioChat(
                "Daniel",
                "ya baja a comer ctm",
                R.drawable.daniel,
                "10:30",
                1
            )
        )
        listaUsuarioChat.add(
            UsuarioChat(
                "Edison",
                "Que fue prro xd",
                R.drawable.edi,
                "10:30",
                1
            )
        )
        listaUsuarioChat.add(
            UsuarioChat(
                "Erick",
                "Ya ponte al Guarson :v",
                R.drawable.fotos2,
                "09:25",
                2
            )
        )
        listaUsuarioChat.add(
            UsuarioChat(
                "José Guarderas",
                "Oe ayuda con un deber xd",
                R.drawable.jose,
                "Ayer",
                2
            )
        )





        iniciarRecyclerView(listaUsuarioChat, MainActivity(), recyclerViewChats)

            return root
        //return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun iniciarRecyclerView(
        lista: ArrayList<UsuarioChat>,
        activity: MainActivity,
        recyclerView: RecyclerView
    ) {
        val adaptador = AdaptadorUsuarioChat(
            activity,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adaptador.notifyDataSetChanged()

        //registerForContextMenu(recyclerView)


    }

}