package com.example.examen2b_estudiante_materia.dto

class EstudianteDto (
    val id: Int,
    val idMateria: Int,
    var numeroUnico: String?,
    var cedula: String?,
    var nombre: String?,
    var carrera: String?,
    var fechaNacimiento: String?,
    var estadoEstudiante: Boolean,
    var latitud: Double?,
    var longitud: Double?,
    var ubucacion: Double?,

        ) {

    override fun toString(): String {
        return super.toString()
    }

}