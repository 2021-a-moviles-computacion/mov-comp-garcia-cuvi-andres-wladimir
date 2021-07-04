import java.util.*

fun main (args: Array<String>){
    println("Hello World")

    var e1: Estudiante = Estudiante(201620469,"1726264961","Andrés",
    "García","Computación", Date(), false
    )
    var e2: Estudiante = Estudiante(201520159,"1726247856","Jaime",
    "Roldos","Software", Date(), true
    )

    e1.insert(e1)
    e1.insert(e2)
//    print(e1)
}