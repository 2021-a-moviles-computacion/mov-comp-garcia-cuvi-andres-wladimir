import java.util.*
import javax.swing.JOptionPane
import kotlin.collections.ArrayList


class Estudiante {

    var numeroUnico: Int = 0
    var cedulaIdentidad: String = ""
    var nombre: String = ""
    var carrera: String = ""
    var fechaNacimiento: Date = Date()
    var activo: Boolean = false

    constructor()



    fun setEstudiante(){
        var aux: Int
        numeroUnico = JOptionPane.showInputDialog("Ingrese el número único: ").toInt()
        cedulaIdentidad =  JOptionPane.showInputDialog("Ingrese la cédula de identidad: ")
        nombre =  JOptionPane.showInputDialog("Ingrese el nombre y apellido: ")
        carrera =  JOptionPane.showInputDialog("Ingrese la carrrera: ")
        fechaNacimiento =  Date()
        //activo =JOptionPane.showInputDialog("Ingrese si está activo (true/false): ").toBoolean()
        aux = JOptionPane.showConfirmDialog(
            null,
            "Registre si el estudiante se encuentra activo (activo -> Sí - inactivo -> No)",
            "Estado",
            JOptionPane.YES_NO_OPTION
        )
        activo=intToBoolean(aux)
    }

    fun search(
        consulta: String,
        datoAConsultar: String,
        listaMateria: ArrayList<Materia>
    ):List<List<Any>?>{
        var materiasHalladas: List<List<Any>?> = emptyList()
        when(consulta){
            "nombre" ->{
                materiasHalladas = listaMateria
                    .map { materia: Materia ->
                        val materia_estudiante = materia.estudiantes?.filter{
                            estudiante: Estudiante ->
                                return@filter estudiante.nombre == datoAConsultar
                        }
                        return@map materia_estudiante.let { listOf<Any>(materia, it) }
                    }.filter { list: List<Any> ->
                        return@filter list != null
                    }.filter { list ->
                        val listaEstudiantes = list?.get(1) as List<Estudiante>
                        return@filter listaEstudiantes.size > 0
                    }
            }

            "cedulaIdentidad" ->{
                materiasHalladas = listaMateria
                    .map { materia: Materia ->
                        val materia_estudiante = materia.estudiantes?.filter{
                                estudiante: Estudiante ->
                            return@filter estudiante.cedulaIdentidad == datoAConsultar
                        }
                        return@map materia_estudiante.let { listOf<Any>(materia, it) }
                    }.filter { list: List<Any> ->
                        return@filter list != null
                    }.filter { list ->
                        val listaEstudiantes = list?.get(1) as List<Estudiante>
                        return@filter listaEstudiantes.size > 0
                    }
            }

            "numeroUnico" ->{
                materiasHalladas = listaMateria
                    .map { materia: Materia ->
                        val materia_estudiante = materia.estudiantes?.filter{
                                estudiante: Estudiante ->
                            return@filter estudiante.numeroUnico.toString() == datoAConsultar
                        }
                        return@map materia_estudiante.let { listOf<Any>(materia, it) }
                    }.filter { list: List<Any> ->
                        return@filter list != null
                    }.filter { list ->
                        val listaEstudiantes = list?.get(1) as List<Estudiante>
                        return@filter listaEstudiantes.size > 0
                    }
            }
            else -> {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado")
            }
        }
        return materiasHalladas
    }


    fun remove(
        cedula: String,
        listaMateria: ArrayList<Materia>
        ):ArrayList<Materia>{
        val indices = indice(cedula, listaMateria)
        val hayMateria = indices["Materia"]!! > -1
        val hayEstudiante = indices["estudiante"]!! > -1

        if (hayMateria && hayEstudiante) {
            val indiceMateria = indices["Materia"] as Int
            val indiceEstudiante = indices["estudiante"] as Int
            listaMateria[indiceMateria].estudiantes?.removeAt(indiceEstudiante)
        }
        return listaMateria
    }

    fun indice(
        cedula: String,
        materias: ArrayList<Materia>
        ): Map<String, Int?> {
            val respuesta = materias
                .map { materia: Materia ->
                    val materia_estudiante = materia.estudiantes?.filter { estudiante: Estudiante ->
                        return@filter estudiante.cedulaIdentidad == cedula
                    }
                    return@map materia_estudiante?.let { listOf<Any>(materia, it) }
                }.filter { list: List<Any>? ->
                    return@filter list != null
                }.filter { list ->
                    val estudiantesHallados = list?.get(1) as List<Estudiante>
                    return@filter estudiantesHallados.size > 0
                }

            val hayRespuesta = respuesta.size > 0
            if (!hayRespuesta) {
                JOptionPane.showMessageDialog(null, "No se encontró al estudiante con cédula: ${cedula}")
                return mapOf<String, Int?>("Materia" to -1, "Estudiante" to -1)
            }
            val indiceMateria = materias.indexOf(respuesta[0]?.get(0))
            val estudiante = respuesta[0]?.get(1) as List<Estudiante>
            val indiceEmpleado = materias[indiceMateria].estudiantes?.indexOf(estudiante[0])
            val indices = mapOf<String, Int?>("Materia" to indiceMateria, "estudiante" to indiceEmpleado)
            return indices

    }


    fun intToBoolean(valor: Int): Boolean {
        if (valor == 1) {
            return false
        } else if (valor == 0) {
            return true
        }
        return false
    }


    override fun toString(): String {
        return "\n\t\t\tEstudiante ->  Número único: $numeroUnico, Cédula de Identidad: $cedulaIdentidad, Nombre: $nombre, Carrera: $carrera, Fecha de Nacimiento: $fechaNacimiento, Estado: $activo  \n\t\t\t"
    }


}