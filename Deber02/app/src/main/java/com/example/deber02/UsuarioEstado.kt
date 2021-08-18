package com.example.deber02

import android.os.Parcel
import android.os.Parcelable

class UsuarioEstado (
    val nombre: String,
    val fecha: String,
    val imagen: Int
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(fecha)
        parcel.writeInt(imagen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UsuarioEstado> {
        override fun createFromParcel(parcel: Parcel): UsuarioEstado {
            return UsuarioEstado(parcel)
        }

        override fun newArray(size: Int): Array<UsuarioEstado?> {
            return arrayOfNulls(size)
        }
    }

}