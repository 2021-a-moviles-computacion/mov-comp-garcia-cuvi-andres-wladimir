package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(
    contexto: Context??
) : SQLiteOpenHelper(
    contexto,
    "examen01",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaMateria = """ 
            create table MATERIA (
               IDMATERIA            integer primary key autoincrement,
               CODIGOMATERIA        varchar(10)           not null,
               NOMBREMATERIA        varchar(50)          not null,
               CREDITOSMATERIA      int                  not null,
               AULAMATERIA          varchar(15)          not null,
               ESTADOMATERIA        bit                  not null
            )           
        """.trimIndent()
        Log.i("bdd", "Creando la tabla de materias")
        db?.execSQL(scriptCrearTablaMateria)

        val scriptCrearTablaEstudiante = """ 
            create table ESTUDIANTE (
               IDESTUDIANTE        integer primary key autoincrement,
               IDMATERIA            int                  ,
               NUMEROUNICOESTUDIANTE varchar(10)          not null,
               CEDULAESTUDIANTE     varchar(10)          not null,
               NOMBREESTUDIANTE     varchar(75)          not null,
               CARRERAESTUDIANTE    varchar(100)         not null,
               FECHANACIMIENTOESTUDIANTE date             not null,
               ESTADOESTUDIANTE     bit                  not null,
               CONSTRAINT FK_ESTUDIAN_RELATIONS_MATERIA
                FOREIGN KEY (IDMATERIA)
                REFERENCES MATERIA(IDMATERIA)
            )        
        """.trimIndent()
        Log.i("bdd", "Creando la tabla de estudiantes")
        db?.execSQL(scriptCrearTablaEstudiante)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun crearMateriaFormulario(
        codigo: String,
        nombre: String,
        creditos: Int,
        aula: String,
        estado: Boolean
    ): Boolean{
        val conexionEscrituta = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("CODIGOMATERIA",codigo)
        valoresAGuardar.put("NOMBREMATERIA",nombre)
        valoresAGuardar.put("CREDITOSMATERIA",creditos)
        valoresAGuardar.put("AULAMATERIA",aula)
        valoresAGuardar.put("ESTADOMATERIA",estado)
        val resultadoEscritura: Long = conexionEscrituta
            .insert(
                "MATERIA",
                null,
                valoresAGuardar
            )
        conexionEscrituta.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

}