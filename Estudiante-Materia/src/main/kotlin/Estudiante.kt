import java.util.*
import javax.swing.JOptionPane




class Estudiante {

    private var numeroUnico: Int = 0
    private var cedulaIdentidad: String = ""
    private var nombre: String = ""
    private var carrera: String = ""
    private var fechaNacimiento: Date = Date()
    private var activo: Boolean = false

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