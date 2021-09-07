package com.example.firebaseuno.dto

import java.text.NumberFormat
import java.util.*

class FirestoreOrdenDto (
    val nombreProducto: String,
    val precioUnitario: Double,
    val cantidad: Int,
        ){
    override fun toString(): String {
        val basePrice: NumberFormat = NumberFormat.getCurrencyInstance()
        basePrice
            .setMaximumFractionDigits(2)
        basePrice
            .setCurrency(Currency.getInstance("USD"))

        return "${nombreProducto}       " +
                "${basePrice.format(precioUnitario)}             " +
                "${cantidad}         " +
                "${basePrice.format(CalcularTotalProductos())} "
    }

    fun CalcularTotalProductos():Double{
        return precioUnitario * cantidad.toDouble()
    }
}