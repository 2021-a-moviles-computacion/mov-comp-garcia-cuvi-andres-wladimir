package com.example.deber02.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.deber02.AdaptadorUsuarioChat
import com.example.deber02.MainActivity
import com.example.deber02.R
import com.example.deber02.UsuarioChat
import com.example.deber02.databinding.FragmentMainBinding

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        var listaUsuarioChat = arrayListOf<UsuarioChat>()
        val recyclerViewChats = binding.rvChats
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
        iniciarRecyclerView(listaUsuarioChat, MainActivity(), recyclerViewChats)


//        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
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