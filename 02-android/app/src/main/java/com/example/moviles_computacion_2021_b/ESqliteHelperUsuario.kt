package com.example.moviles_computacion_2021_b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperUsuario(
    context: Context?,
) : SQLiteOpenHelper(
    context,
    "moviles",
    null,
    1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaUsuario = """ 
            CREATE TABLE USUARIO (
                id INTEGER PRIMARY KEY AUTOINCREMENT, 
                nombre VARCHAR(50),
                descripcion VARCHAR(50)
            )            
        """.trimIndent()
        Log.i("bdd","Creando la tabla de usuario")

        db?.execSQL(scriptCrearTablaUsuario)

        fun crearUsuarioFormulario(
            nombre: String,
            descripcion: String
        ): Boolean{
            val conexionEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombre", nombre)
            valoresAGuardar.put("descripcion", descripcion)
            val resultadoEscritura: Long = conexionEscritura
                .insert(
                    "USUARIO",
                    null,
                    valoresAGuardar
                )
            conexionEscritura.close()
            return if (resultadoEscritura.toInt() == -1) false else true
        }


        fun consultarUsuarioPorId(id: Int): EUsuarioBDD{
            val scriptConsultarUsuario = "SELECT * FROM USUARIO WHERE ID = ${id}"
            val baseDatosLectura = readableDatabase
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultarUsuario,
                null
            )
            val existeUsuario = resultadoConsultaLectura.moveToFirst()
            //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
            val usuarioEncontrado = EUsuarioBDD(0,"","")
            if (existeUsuario){
                do{
                    val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                    val nombre = resultadoConsultaLectura.getString(1) //Columna indice 1 -> NOMBRE
                    val descripcion = resultadoConsultaLectura.getString(2) //Columna indice 2 -> DESCRIPCION

                    if(id!=null){
                        usuarioEncontrado.id = id
                        usuarioEncontrado.nombre = nombre
                        usuarioEncontrado.descripcion = descripcion
                        //arregloUsuario.add(usuarioEncontrado)
                    }
                }while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return usuarioEncontrado
        }

        fun eliminarUsuarioFormulario (id: Int): Boolean {

            //val conexionEscritura = this.writableDatabase
            val conexionEscritura = writableDatabase
            var resultadoEliminacion = conexionEscritura
                .delete(
                    "USUARIO",
                    "id=?",
                    arrayOf()
                )
            conexionEscritura.close()
            return if (resultadoEliminacion.toInt() == -1) false else true
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }


}