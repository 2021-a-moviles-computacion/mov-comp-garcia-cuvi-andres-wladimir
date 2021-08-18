package com.example.deber02

import android.os.Parcel
import android.os.Parcelable

class UsuarioLlamada (
    val nombre: String,
    val fecha: String,
    val imagen: Int,
    val estado: Int
        ): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(fecha)
        parcel.writeInt(imagen)
        parcel.writeInt(estado)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UsuarioLlamada> {
        override fun createFromParcel(parcel: Parcel): UsuarioLlamada {
            return UsuarioLlamada(parcel)
        }

        override fun newArray(size: Int): Array<UsuarioLlamada?> {
            return arrayOfNulls(size)
        }
    }
}