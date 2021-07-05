import java.io.*


class ManejoArchivos {

    val path = "E:\\MateriasUniversidad\\Aplicaciones Moviles\\Repositorios\\mov-comp-garcia-cuvi-andres-wladimir\\Estudiante-Materia\\src\\main\\resources\\Deber01.txt"
    fun read() {

        val file = File(path)
        file.forEachLine { println(it) }
        println("-------------------------------------------------------------------------")
    }
    fun write(text: String) {
        File(path).appendText(text)
    }


}