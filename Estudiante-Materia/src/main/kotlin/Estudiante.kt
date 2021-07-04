import java.util.*

class Estudiante (
    private val numeroUnico: Int,
    private val cedulaIdentidad: String,
    private val nombre: String,
    private val apellido: String,
    private val carrera: String,
    private val fechaNacimiento: Date,
    private val activo: Boolean
) {
    val list = mutableListOf<Estudiante>()

    fun insert(estudiante: Estudiante){
        list.add(estudiante)
        println(list)
    }

    fun remove(estudiante: Estudiante){
//        list.removeIf(estudiante.nombre<)

    }

    override fun toString(): String {
        return "Estudiante(numeroUnico=$numeroUnico, cedulaIdentidad='$cedulaIdentidad', nombre='$nombre', apellido='$apellido', carrera='$carrera', fechaNacimiento=$fechaNacimiento, activo=$activo)"
    }


}