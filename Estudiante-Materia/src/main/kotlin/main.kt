import javax.swing.JOptionPane

fun main(args: Array<String>) {

    var listaMateria: Materia = Materia()
    var arc = ManejoArchivos()
    arc.read()
    menuEstructurado(listaMateria)
    arc.read()


}


fun menuEstructurado(listaMateria: Materia) {

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
                    menuEstudiantes()
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
//
//                    JOptionPane.showMessageDialog(
//                        null,
//                      "XD"
//                    )
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


fun menuEstudiantes() {
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


//                    JOptionPane.showMessageDialog(
//                        null,
//                        "XD"
//                    )
                }
                3 -> {
//
//                    JOptionPane.showMessageDialog(
//                        null,
//                        "XD"
//                    )
                }
                4 -> {

//                    JOptionPane.showMessageDialog(
//                        null,
//                       "XD"
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



