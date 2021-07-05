package com.example.moviles_computacion_2021_b

import android.os.Parcel
import android.os.Parcelable

class DLiga(
    val nombre: String?,
    val descripcion: String?,
    val liga: DLiga?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(DLiga::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
        parcel?.writeParcelable(liga, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DLiga> {
        override fun createFromParcel(parcel: Parcel): DLiga {
            return DLiga(parcel)
        }

        override fun newArray(size: Int): Array<DLiga?> {
            return arrayOfNulls(size)
        }
    }

}