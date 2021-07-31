package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(
    contexto: Context?
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
               CODIGOMATERIA        varchar(10)           not null UNIQUE,
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
               NUMEROUNICOESTUDIANTE varchar(10)          not null UNIQUE,
               CEDULAESTUDIANTE     varchar(10)          not null UNIQUE,
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
        //val materiaEncontrada = Materia("", "", 0, "", true)
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMateriaPorCodigo,
            null
        )

        val existeMateria = resultadoConsultaLectura.moveToFirst()
        if (existeMateria) {
            do {
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val codigo = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val nombre = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val creditos = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val aula = resultadoConsultaLectura.getString(4) //Columna indice 4 -> AULA
                val estado = (resultadoConsultaLectura.getInt(5)) > 0 //Columna indice 5 -> ESTADO

                if (codigo != null) {
                    listaMateria.add(Materia(id, codigo, nombre, creditos, aula, estado))
                }
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaMateria
    }


    fun consultarMateriaPorId(id: Int): Materia {

        val scriptConsultarMateriaPorId =
            "SELECT * FROM MATERIA WHERE IDMATERIA = '${id}'"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMateriaPorId,
            null
        )
        val existeMateria = resultadoConsultaLectura.moveToFirst()
        //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
        val materiaEncontrada = Materia(0, "", "", 0, "", true)
        if (existeMateria) {
            do {
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val codigo = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val nombre = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val creditos = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val aula = resultadoConsultaLectura.getString(4) //Columna indice 4 -> AULA
                val estado = (resultadoConsultaLectura.getInt(5)) > 0 //Columna indice 5 -> ESTADO

                if (codigo != null) {
                    materiaEncontrada.id = id
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


    fun consultarMateriaPorCodigo(codigo: String): Materia {
        val scriptConsultarMateriaPorCodigo =
            "SELECT * FROM MATERIA WHERE CODIGOMATERIA = '${codigo}'"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarMateriaPorCodigo,
            null
        )
        val existeMateria = resultadoConsultaLectura.moveToFirst()
        //val arregloUsuario = arrayListOf<EUsuarioBDD>()       //En caso de3 necesitar un arreglo de registros
        val materiaEncontrada = Materia(0, "", "", 0, "", true)
        if (existeMateria) {
            do {
                val id = resultadoConsultaLectura.getInt(0) //Columna indice 0 -> ID
                val codigo = resultadoConsultaLectura.getString(1) //Columna indice 1 -> CODIGO
                val nombre = resultadoConsultaLectura.getString(2) //Columna indice 2 -> NOMBRE
                val creditos = resultadoConsultaLectura.getInt(3) //Columna indice 3 -> CREDITOS
                val aula = resultadoConsultaLectura.getString(4) //Columna indice 4 -> AULA
                val estado = (resultadoConsultaLectura.getInt(5)) > 0 //Columna indice 5 -> ESTADO

                if (codigo != null) {
                    materiaEncontrada.id = id
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
        return if (resultadoEliminacion.toInt() != -1) {
            Log.i("bdd", "Materia ELiminada -> ${codigo}")
            true
        } else {
            Log.i("bdd", "NO SE PUDO ELIMINAR ")
            false
        }
    }

    fun eliminarMateriaPorId(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "MATERIA",
                "IDMATERIA=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() != -1) {
            Log.i("bdd", "Materia ELiminada -> ${id}")
            true
        } else {
            Log.i("bdd", "NO SE PUDO ELIMINAR ")
            false
        }

    }

    fun actualizarMateriaFormulario(
        idActualizar: Int,
        codigo: String,
        nombre: String,
        creditos: Int,
        aula: String,
        estado: Boolean
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("CODIGOMATERIA", codigo)
        valoresAActualizar.put("NOMBREMATERIA", nombre)
        valoresAActualizar.put("CREDITOSMATERIA", creditos)
        valoresAActualizar.put("AULAMATERIA", aula)
        valoresAActualizar.put("ESTADOMATERIA", estado)
        val resultadoActualización = conexionEscritura
            .update(
                "MATERIA",
                valoresAActualizar,
                "IDMATERIA=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualización.toInt() == -1) false else true
    }


//    Métodos para Estudiante

    fun crearEstudianteFormulario(
        idMateria: Int,
        numeroUnico: String,
        cedula: String,
        nombre: String,
        carrera: String,
        fechaNacimiento: String,
        estadoEstudiante: Boolean
    ): Boolean {
        val conexionEscrituta = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("IDMATERIA", idMateria)
        valoresAGuardar.put("NUMEROUNICOESTUDIANTE", numeroUnico)
        valoresAGuardar.put("CEDULAESTUDIANTE", cedula)
        valoresAGuardar.put("NOMBREESTUDIANTE", nombre)
        valoresAGuardar.put("CARRERAESTUDIANTE", carrera)
        valoresAGuardar.put("FECHANACIMIENTOESTUDIANTE", fechaNacimiento)
        valoresAGuardar.put("ESTADOESTUDIANTE", estadoEstudiante)
        val resultadoEscritura: Long = conexionEscrituta
            .insert(
                "ESTUDIANTE",
                null,
                valoresAGuardar
            )
        conexionEscrituta.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultaListaEstudiantes(): ArrayList<Estudiante> {
        val scriptConsultarEstudiante = "SELECT * FROM ESTUDIANTE"
        val baseDatosLectura = readableDatabase
        val listaEstudiante = arrayListOf<Estudiante>()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarEstudiante,
            null
        )

        val existeEstudiante = resultadoConsultaLectura.moveToFirst()


        if (existeEstudiante) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val idMateria = resultadoConsultaLectura.getInt(1)
                val numeroUnico = resultadoConsultaLectura.getString(2)
                val cedula = resultadoConsultaLectura.getString(3)
                val nombre = resultadoConsultaLectura.getString(4)
                val carrera = resultadoConsultaLectura.getString(5)
                val fechaNacimiento = resultadoConsultaLectura.getString(6)
                val estadoEstudiante = (resultadoConsultaLectura.getInt(7)) > 0

                if (id != null) {
                    listaEstudiante.add(
                        Estudiante(
                            id,
                            idMateria,
                            numeroUnico,
                            cedula,
                            nombre,
                            carrera,
                            fechaNacimiento,
                            estadoEstudiante
                        )
                    )
                }

            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaEstudiante
    }


    fun consultarEstudiantePorId(id: Int):  ArrayList<Estudiante> {

        val scriptConsultarEstudiantePorId =
            "SELECT * FROM ESTUDIANTE WHERE IDESTUDIANTE = '${id}'"
        val baseDatosLectura = readableDatabase
        val listaEstudiante = arrayListOf<Estudiante>()
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarEstudiantePorId,
            null
        )
        val existeEstudiante = resultadoConsultaLectura.moveToFirst()

        if (existeEstudiante) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val idMateria = resultadoConsultaLectura.getInt(1)
                val numeroUnico = resultadoConsultaLectura.getString(2)
                val cedula = resultadoConsultaLectura.getString(3)
                val nombre = resultadoConsultaLectura.getString(4)
                val carrera = resultadoConsultaLectura.getString(5)
                val fechaNacimiento = resultadoConsultaLectura.getString(6)
                val estadoEstudiante = (resultadoConsultaLectura.getInt(7)) > 0

                if (id != null) {
                    listaEstudiante.add(
                        Estudiante(
                            id,
                            idMateria,
                            numeroUnico,
                            cedula,
                            nombre,
                            carrera,
                            fechaNacimiento,
                            estadoEstudiante
                        )
                    )
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        Log.i("bdd", resultadoConsultaLectura.toString())
        return listaEstudiante
    }

    fun eliminarEstudiantePorId(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ESTUDIANTE",
                "IDESTUDIANTE=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() != -1) {
            Log.i("bdd", "Estudiante ELiminada -> ${id}")
            true
        } else {
            Log.i("bdd", "NO SE PUDO ELIMINAR ")
            false
        }

    }


    fun actualizarEstudianteFormulario(
        idActualizar: Int,
        idMateria: Int,
        numeroUnico: String,
        cedula: String,
        nombre: String,
        carrera: String,
        fechaNacimiento: String,
        estado: Boolean
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("IDMATERIA", idMateria)
        valoresAActualizar.put("NUMEROUNICOESTUDIANTE", numeroUnico)
        valoresAActualizar.put("CEDULAESTUDIANTE", cedula)
        valoresAActualizar.put("NOMBREESTUDIANTE", nombre)
        valoresAActualizar.put("CARRERAESTUDIANTE", carrera)
        valoresAActualizar.put("FECHANACIMIENTOESTUDIANTE", fechaNacimiento)
        valoresAActualizar.put("ESTADOESTUDIANTE", estado)
        val resultadoActualización = conexionEscritura
            .update(
                "ESTUDIANTE",
                valoresAActualizar,
                "IDESTUDIANTE=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualización.toInt() == -1) false else true
    }

}