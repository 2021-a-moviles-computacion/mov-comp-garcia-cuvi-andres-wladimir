package com.example.firebaseuno.dto

class FirestoreRestauranteDto (
    var uid:String? = null,
    var nombre:String? = null
        ){
    override fun toString():String{
        return nombre!!
    }
}