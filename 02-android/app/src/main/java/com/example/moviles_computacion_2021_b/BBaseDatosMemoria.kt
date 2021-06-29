package com.example.moviles_computacion_2021_b

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(BEntrenador("Andres", "a@a.com"))
            arregloBEntrenador
                .add(BEntrenador("Daniel", "b@b.com"),)
            arregloBEntrenador
                .add(BEntrenador("Santiago", "c@c.com"))

        }
    }
}