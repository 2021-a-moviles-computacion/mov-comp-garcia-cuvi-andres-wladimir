package com.example.examen01

import android.os.Parcel
import android.os.Parcelable


class Estudiante(
    val id: Int,
    val idMateria: Int,
    var numeroUnico: String?,
    var cedula: String?,
    var nombre: String?,
    var carrera: Int?,
    var fechaNacimiento: String?,
    var materiaActiva: Boolean
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(idMateria)
        parcel.writeString(numeroUnico)
        parcel.writeString(cedula)
        parcel.writeString(nombre)
        parcel.writeValue(carrera)
        parcel.writeString(fechaNacimiento)
        parcel.writeByte(if (materiaActiva) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Estudiante> {
        override fun createFromParcel(parcel: Parcel): Estudiante {
            return Estudiante(parcel)
        }

        override fun newArray(size: Int): Array<Estudiante?> {
            return arrayOfNulls(size)
        }
    }

}