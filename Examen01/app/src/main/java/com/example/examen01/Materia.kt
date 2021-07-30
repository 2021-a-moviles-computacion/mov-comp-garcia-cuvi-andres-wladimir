package com.example.examen01

import android.os.Parcel
import android.os.Parcelable

class Materia (
    var id: Int,
    var codigo: String?,
    var nombre: String?,
    var creditos: Int?,
    var aula: String?,
    var materiaActiva: Boolean
) : Parcelable{
    constructor(parcel: Parcel) : this(
        (parcel.readValue(Int::class.java.classLoader) as? Int)!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(codigo)
        parcel.writeString(nombre)
        parcel.writeValue(creditos)
        parcel.writeString(aula)
        parcel.writeByte(if (materiaActiva) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Materia> {
        override fun createFromParcel(parcel: Parcel): Materia {
            return Materia(parcel)
        }

        override fun newArray(size: Int): Array<Materia?> {
            return arrayOfNulls(size)
        }
    }


}