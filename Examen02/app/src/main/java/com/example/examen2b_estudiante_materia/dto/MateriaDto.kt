package com.example.examen2b_estudiante_materia.dto

import android.os.Parcel
import android.os.Parcelable

class MateriaDto (
    var aula: String?= null,
    var codigo: String? = null,
    var creditos: Int?= null,
    var materiaActiva: Boolean? = null,
    var nombre: String? = null,
    var uid: String? = null
        ):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readByte() != 0.toByte(),
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
        parcel.writeString(aula)
        parcel.writeString(codigo)
        parcel.writeValue(creditos)
        parcel.writeByte(if (materiaActiva!!) 1 else 0)
        parcel.writeString(nombre)
        parcel.writeString(uid)
    }

    companion object CREATOR : Parcelable.Creator<MateriaDto> {
        override fun createFromParcel(parcel: Parcel): MateriaDto {
            return MateriaDto(parcel)
        }

        override fun newArray(size: Int): Array<MateriaDto?> {
            return arrayOfNulls(size)
        }
    }
}