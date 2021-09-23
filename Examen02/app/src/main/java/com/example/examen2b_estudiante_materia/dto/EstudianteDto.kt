package com.example.examen2b_estudiante_materia.dto

import android.os.Parcel
import android.os.Parcelable

class EstudianteDto (
    var carrera: String?= null,
    var cedula: String?= null,
    var estadoEstudiante: Boolean?= null,
    var fechaNacimiento: String?= null,
    var idMateria: String? = null,
    var latitud: Double?= null,
    var longitud: Double?= null,
    var nombre: String?= null,
    var numeroUnico: String? = null,
    var uid: String? = null
        ):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(carrera)
        parcel.writeString(cedula)
        parcel.writeByte(if (estadoEstudiante!!) 1 else 0)
        parcel.writeString(fechaNacimiento)
        parcel.writeString(idMateria)
        parcel.writeValue(latitud)
        parcel.writeValue(longitud)
        parcel.writeString(nombre)
        parcel.writeString(numeroUnico)
        parcel.writeString(uid)
    }

    companion object CREATOR : Parcelable.Creator<EstudianteDto> {
        override fun createFromParcel(parcel: Parcel): EstudianteDto {
            return EstudianteDto(parcel)
        }

        override fun newArray(size: Int): Array<EstudianteDto?> {
            return arrayOfNulls(size)
        }
    }

}