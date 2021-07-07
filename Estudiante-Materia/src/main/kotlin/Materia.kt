import javax.swing.JOptionPane


class Materia {

    var codigo: String = ""
    var nombre: String = ""
    var creditos: Int = 0
    var aula: String = ""
    var materiaActiva: Boolean = false
    var estudiantes: ArrayList<Estudiante> = arrayListOf()
    var materias: ArrayList<Materia> = arrayListOf()


    fun insert(materia: Materia) {
        materias.add(materia)
        println("IIINIIICIOOOO" + materias + "FFIIIIINNN")
    }

    fun setMateria() {
        var opcion: Int
        var aux: Int

        codigo = JOptionPane.showInputDialog("Ingrese el código de la Materia")
        nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Materia")
        creditos = JOptionPane.showInputDialog("Ingrese el número de créditos").toInt()
        aula = JOptionPane.showInputDialog("Ingrese la ubicacición (Aula)")
        //materiaActiva = JOptionPane.showInputDialog("Ingrese si está activa (true/false)").toBoolean()
        aux = JOptionPane.showConfirmDialog(
            null,
            "Registre el estado de la materia (activa -> Sí - inactiva -> No)",
            "Estado",
            JOptionPane.YES_NO_OPTION
        )
        materiaActiva = intToBoolean(aux)
        JOptionPane.showMessageDialog(null, "Se procede al registro de estudiantes de esta materia...")
        do {
            val estudiante = Estudiante()
            estudiante.setEstudiante()
            estudiantes.add(estudiante)
            opcion = JOptionPane.showConfirmDialog(
                null,
                "Desea ingresar mas estudiantes?...",
                "Ingreso de estudiantes",
                JOptionPane.YES_NO_OPTION
            )


        } while (opcion == JOptionPane.YES_OPTION)
    }


    fun search(consulta: String): List<Materia> {
        val filteredList = ArrayList<Materia>()
        materias.forEach { materia ->
            if (materia.nombre == consulta) {
                filteredList.add(materia)
            } else if (materia.nombre != consulta) {
                JOptionPane.showMessageDialog(
                    null,
                    "No se encuentra esa materia"
                )
            }
        }
        return filteredList
    }

    fun remove() {

        JOptionPane.showMessageDialog(
            null,
            "Aquí puede ver los nombres de " +
                    "las Materias para saber cual desea eliminar: \n" +
                    "$materias"
        )
        val consulta = JOptionPane.showInputDialog("Ingrese el nombre de la materia que desea eliminar")
        materias.forEach { materia ->
            if (materia.nombre == consulta) {
                materias.remove(materia)
                JOptionPane.showMessageDialog(
                    null,
                    "---MATERIAS ACTUALIZADAS ---" +
                            "$materias"
                )
            } else if (materia.nombre != consulta) {
                JOptionPane.showMessageDialog(
                    null,
                    "No se encuentra esa materia"

                )
            }
        }
    }

    fun edit(
        nombre: String,
        datoEditar: String,
        nuevoDato: String
    ) {

        val indice = indice(nombre)
        val existeMateria = indice > -1
        if (existeMateria) {
            when (datoEditar) {
                "codigo" -> {
                    materias[indice].codigo = nuevoDato
                }
                "nombre" -> {
                    materias[indice].nombre = nuevoDato
                }
                "aula" -> {
                    materias[indice].aula = nuevoDato
                }
                "creditos" -> {
                    materias[indice].creditos = nuevoDato.toInt()
                }
                else -> {
                    JOptionPane.showMessageDialog(null, "No se encontró la materia solicitada ->  ${datoEditar}")
                }
            }
        }
        JOptionPane.showMessageDialog(
            null,
            "---MATERIAS ACTUALIZADAS ---" +
                    "$materias"
        )
    }

    fun intToBoolean(valor: Int): Boolean {
        if (valor == 1) {
            return false
        } else if (valor == 0) {
            return true
        }
        return false
    }

    fun indice(nombre: String): Int {
        val respuesta = materias.filter { materia: Materia ->
            return@filter materia.nombre == nombre
        }
        val existeMateria = respuesta.size > 0
        if (!existeMateria) {
            JOptionPane.showMessageDialog(null, "No se encontro la empresa  ${nombre}")
            return -1
        }
        return materias.indexOf(respuesta[0])

    }

    override fun toString(): String {
        return "Materia -> Código: $codigo  Nombre: $nombre, Créditos: " +
                "$creditos, Aula: $aula, Estado: $materiaActiva" +
                "\n\t\t\tLista de estudiantes:\n\t\t\t$estudiantes"
    }


}