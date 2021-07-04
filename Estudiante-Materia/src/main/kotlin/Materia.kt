import java.util.*
class Materia (
    private val codigoMateria: String = "",
    private val nombreMateria: String = "",
    private val creditos: Int = 0,
    private val aula: String = "",
    private val materiaActiva : Boolean = false,
    )
{


    override fun toString(): String {
        return "Materia(codigoMateria='$codigoMateria', nombreMateria='$nombreMateria', creditos=$creditos, aula='$aula', materiaActiva=$materiaActiva)"
    }


}