import javax.swing.JOptionPane

fun main(args: Array<String>) {

    var listaMateria: Materia = Materia()
    var listaEstudiante: Estudiante = Estudiante()
    var arc = ManejoArchivos()
    arc.read()
    menuEstructurado(listaMateria, listaEstudiante)
    arc.read()


}


fun menuEstructurado(listaMateria: Materia, listaEstudiante: Estudiante) {

    var select = -1 //opción elegida del usuario
    while (select != 0) {
        //Try catch para evitar que el programa termine si hay un error
        try {
            val lectura = JOptionPane.showInputDialog(
                null, """
         Elige opción:
     1.- Menú de Materias
     2.- Menú de Estudiantes
     0.- Salir
     
     """.trimIndent()
            )
            //Recoger una variable por consola
            select = lectura.toInt()
            when (select) {
                1 -> {
                    menuMaterias(listaMateria)
                }
                2 -> {
                    menuEstudiantes(listaEstudiante, listaMateria)
                }
                0 -> JOptionPane.showMessageDialog(null, "Adios!")
                else -> JOptionPane.showMessageDialog(null, "Número no reconocido")
            }
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Uoop! Error!")
        }
    }
}


fun menuMaterias(listaMateria: Materia) {

    //Mientras la opción elegida sea 0, preguntamos al usuario
    var select = -1
    while (select != 0) {
        //Try catch para evitar que el programa termine si hay un error
        try {
            val lectura = JOptionPane.showInputDialog(
                null, """
          --- MENÚ DE MATERIAS ---
        Elige opción:
     1.- Registrar
     2.- Buscar
     3.- Modificar
     4.- Eliminar
     0.- Regresar al menú principal
     
     """.trimIndent()
            )
            //Recoger una variable por consola
            select = lectura.toInt()
            when (select) {
                1 -> {
                    var archivos = ManejoArchivos()
                    var materia: Materia = Materia()
                    materia.setMateria()
                    listaMateria.insert(materia)
                    var salida = ""
                    salida += "\n$materia"

                    JOptionPane.showMessageDialog(
                        null,
                        "$salida"
                    )
                    archivos.write(salida)
                }
                2 -> {
                    val consulta = JOptionPane.showInputDialog("Ingrese el nombre de la materia que desea buscar")
                    JOptionPane.showMessageDialog(
                        null,
                        listaMateria.search(consulta)
                    )
                }
                3 -> {
                    JOptionPane.showMessageDialog(
                        null,
                        "Aquí puede ver los nombres de " +
                                "las Materias para saber cual desea actualizar: \n" +
                                "${listaMateria.materias}"
                    )
                    val nombre = JOptionPane.showInputDialog("Ingrese nombre de la materia que desea modificar")
                    val datoAModificar =
                        JOptionPane.showInputDialog("Ingrese el campo de la materia que desea modificar")
                    val nuevoValor = JOptionPane.showInputDialog("Ingrese el contenido modificado")
                    listaMateria.edit(nombre, datoAModificar, nuevoValor)

                }
                4 -> {
                    listaMateria.remove()
//                    JOptionPane.showMessageDialog(
//                        null,
//                        "XD"
//                    )
                }
                0 -> JOptionPane.showMessageDialog(null, "Adios!")
                else -> JOptionPane.showMessageDialog(null, "Número no reconocido")
            }
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Uoop! Error!")
        }
    }
}


fun menuEstudiantes(listaEstudiante: Estudiante, listaMateria: Materia) {
    //Mientras la opción elegida sea 0, preguntamos al usuario
    var select = -1
    while (select != 0) {
        //Try catch para evitar que el programa termine si hay un error
        try {
            val lectura = JOptionPane.showInputDialog(
                null, """
          --- MENÚ DE ESTUDIANTES ---
        Elige opción:
     1.- Registrar
     2.- Buscar
     3.- Modificar
     4.- Eliminar
     0.- Regresar al menú principal
     
     """.trimIndent()
            )
            //Recoger una variable por consola
            select = lectura.toInt()
            when (select) {
                1 -> {

                    var materia: Materia = Materia()
                    materia.setMateria()
                    var salida = ""
                    salida += "\n$materia"
                    var estudiante: Estudiante = Estudiante()



                    JOptionPane.showMessageDialog(
                        null,
                        "$salida"
                    )
                }
                2 -> {

                    val campo = JOptionPane.showInputDialog("Ingrese el campo por el que desea realizar la búsqueda")
                    val consulta = JOptionPane.showInputDialog("Ingrese el valor que quiere buscar")
                    val estudiantesHallados = listaEstudiante.search(campo, consulta, listaMateria.materias)
                    var salida = ""
                    estudiantesHallados.forEach { list ->
                        val materia: Materia = list?.get(0) as Materia
                        val listaEstudiantes: List<Estudiante> = list?.get(1) as List<Estudiante>
                        val nombreEstudiantes = listaEstudiantes.map {
                            estudiante: Estudiante ->
                            val listaRespuestas = mutableMapOf<String, Any>()
                            listaRespuestas.put("numeroUnico", estudiante.numeroUnico)
                            listaRespuestas.put("cedulaIdentidad", estudiante.cedulaIdentidad)
                            listaRespuestas.put("nombre", estudiante.nombre)
                            listaRespuestas.put("carrera", estudiante.carrera)
                            listaRespuestas.put("fechaNacimiento", estudiante.fechaNacimiento)
                            listaRespuestas.put("activo", estudiante.activo)
                            return@map listaRespuestas
                        }
                        salida += "Materia: ${materia.nombre}\n" +
                                "Estudiantes: ${nombreEstudiantes}\n"
                    }

                    JOptionPane.showMessageDialog(
                        null,
                        "$salida"
                    )
                }
                3 -> {
//
//                    JOptionPane.showMessageDialog(
//                        null,
//                        "XD"
//                    )
                }
                4 -> {
                    val cedula = JOptionPane.showInputDialog("Ingrese el numero de cédula del estudiante que desea eliminar")
                    val salida = listaEstudiante.remove(cedula, listaMateria.materias)
                    JOptionPane.showMessageDialog(
                        null,
                        "---MATERIAS ACTUALIZADAS ---" +
                                "$salida    "
                    )
                }
                0 -> JOptionPane.showMessageDialog(null, "Adios!")
                else -> JOptionPane.showMessageDialog(null, "Número no reconocido")
            }
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(null, "Uoop! Error!")
        }
    }


}



