package com.example.moviles_computacion_2021_b

class BEntrenador (val nombre: String,
                   val descripcion: String) {

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }

}