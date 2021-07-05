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

//    fun setEstudiante() {
//        println("Creando un nuevo Estudiante...")
//        println("Ingrese el número único: ")
//        numeroUnico = readLine()!!.toInt()
//        println("Ingrese la cédula de identidad: ")
//        cedulaIdentidad = readLine().toString()
//        println("Ingrese el nombre: ")
//        nombre = readLine().toString()
//        println("Ingrese la carrera: ")
//        carrera = readLine().toString()
//        println("Ingrese la fecha de nacimiento con formato (dd/mm/aaaa): ")
//        fechaNacimiento = Date()
//        println("Ingrese si está activo (true/false): ")
//        activo = readLine().toBoolean()
//    }

    fun setEstudiante(){
        numeroUnico = JOptionPane.showInputDialog("Ingrese el número único: ").toInt()
        cedulaIdentidad =  JOptionPane.showInputDialog("Ingrese la cédula de identidad: ")
        nombre =  JOptionPane.showInputDialog("Ingrese el nombre y apellido: ")
        carrera =  JOptionPane.showInputDialog("Ingrese la carrrera: ")
        fechaNacimiento =  Date()
        activo =JOptionPane.showInputDialog("Ingrese si está activo (true/false): ").toBoolean()
    }



//    fun insert(estudiante: Estudiante){
//        list.add(estudiante)
//        println(list)
//    }

//    fun insert(){
//
//        println("Creando un nuevo Estudiante...")
//        println("Ingrese el número único: ")
//        numeroUnico = readLine()!!.toInt()
//        println("Ingrese la cédula de identidad: ")
//        cedulaIdentidad
//        println("Ingrese el nombre: ")
//        nombre
//        println("Ingrese el apellido: ")
//        apellido
//        println("Ingrese la carrera: ")
//        carrera
//        println("Ingrese la fecha de nacimiento: ")
//        fechaNacimiento
//        println("Ingrese si está activo: ")
//        activo
//    }



    override fun toString(): String {
        return "\n\t\t\tEstudiante ->  Número único: $numeroUnico, Cédula de Identidad: $cedulaIdentidad, Nombre: $nombre, Carrera: $carrera, Fecha de Nacimiento: $fechaNacimiento, Estado: $activo  \n\t\t\t"
    }


}