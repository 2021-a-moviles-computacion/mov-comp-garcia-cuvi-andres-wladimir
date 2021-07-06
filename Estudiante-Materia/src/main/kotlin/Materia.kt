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

        codigo = JOptionPane.showInputDialog("Ingrese el código de la Materia")
        nombre = JOptionPane.showInputDialog("Ingrese el nombre de la Materia")
        creditos = JOptionPane.showInputDialog("Ingrese el número de créditos").toInt()
        aula = JOptionPane.showInputDialog("Ingrese la ubicacición (Aula)")
        materiaActiva = JOptionPane.showInputDialog("Ingrese si está activa (true/false)").toBoolean()
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


    override fun toString(): String {
        return "Materia -> Código: $codigo  Nombre: $nombre, Créditos: " +
                "$creditos, Aula: $aula, Estado: $materiaActiva" +
                "\n\t\t\tLista de estudiantes:\n\t\t\t$estudiantes"
    }


}