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
    ): Boolean {
        val conexionEscrituta = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("CODIGOMATERIA", codigo)
        valoresAGuardar.put("NOMBREMATERIA", nombre)
        valoresAGuardar.put("CREDITOSMATERIA", creditos)
        valoresAGuardar.put("AULAMATERIA", aula)
        valoresAGuardar.put("ESTADOMATERIA", estado)
        val resultadoEscritura: Long = conexionEscrituta
            .insert(
                "MATERIA",
                null,
                valoresAGuardar
            )
        conexionEscrituta.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }


    fun consultaMateriaTotal(): ArrayList<Materia> {
        val scriptConsultarMateriaPorCodigo = "SELECT * FROM MATERIA"
        val baseDatosLectura = readableDatabase
        val listaMateria = arrayListOf<Materia>()
        val materiaEncontrada = Materia("", "", 0, "", true)
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMateriaPorCodigo,
            null
        )

        val existeMateria = resultadoConsultaLectura.moveToFirst()
        if (existeMateria) {
            do {
                val codigo = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val nombre = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val creditos = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val aula = resultadoConsultaLectura.getString(4) //Columna indice 4 -> AULA
                val estado = (resultadoConsultaLectura.getInt(5)) > 0 //Columna indice 5 -> ESTADO

                if (codigo != null) {
                    listaMateria.add(Materia(codigo, nombre, creditos, aula, estado))
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaMateria
    }


    /*fun consultarMateriaPorCodigo(codigo: String): Materia {
        val scriptConsultarMateriaPorCodigo = "SELECT * FROM MATERIA WHERE CODIGOMATERIA = ${codigo}"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMateriaPorCodigo,
            null
        )
        val existeMateria = resultadoConsultaLectura.moveToFirst()
        //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
        val materiaEncontrada = Materia("", "", 0, "", true)
        if (existeMateria) {
            do {
                val codigo = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val nombre = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val creditos = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val aula = resultadoConsultaLectura.getString(4) //Columna indice 4 -> AULA
                val estado = (resultadoConsultaLectura.getInt(5))>0 //Columna indice 5 -> ESTADO

                if (codigo != null) {
                    materiaEncontrada.codigo = codigo
                    materiaEncontrada.nombre = nombre
                    materiaEncontrada.creditos = creditos
                    materiaEncontrada.aula = aula
                    materiaEncontrada.materiaActiva = estado
                    //arregloUsuario.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()

        return materiaEncontrada
    }
*/

    fun eliminarMateriaPorCodigo(codigo: String): Boolean {

        //val conexionEscritura = this.writableDatabase
        val conexionEscritura = writableDatabase
        var resultadoEliminacion = conexionEscritura
            .delete(
                "MATERIA",
                "CODIGOMATERIA=?",
                arrayOf(codigo)
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
        //return resultadoEliminacion.toInt() != -1
    }

    /*fun actualizarMateriaFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val resultadoActualización = conexionEscritura
            .update(
                "USUARIO",
                valoresAActualizar,
                "id=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualización.toInt() == -1) false else true
    }
*/

}