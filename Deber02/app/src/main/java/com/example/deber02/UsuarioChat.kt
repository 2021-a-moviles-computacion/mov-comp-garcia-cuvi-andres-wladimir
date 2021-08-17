package com.example.deber02

import android.os.Parcel
import android.os.Parcelable

class UsuarioChat(
    val nombre: String,
    val chat: String,
    val imagen: Int,
    val fecha: String,
    val numMensajes: Int
        ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(chat)
        parcel.writeInt(imagen)
        parcel.writeString(fecha)
        parcel.writeInt(numMensajes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UsuarioChat> {
        override fun createFromParcel(parcel: Parcel): UsuarioChat {
            return UsuarioChat(parcel)
        }

        override fun newArray(size: Int): Array<UsuarioChat?> {
            return arrayOfNulls(size)
        }
    }

}