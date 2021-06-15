import java.util.*
import kotlin.collections.ArrayList

fun main() {
    println("Hola Mundo")


//    Tipo nombre = valor
//    Int edad = 12; Los puntos y comas son opcionales.
//    Definimos la primera variable

//    Duck Typing

    var edadProfesor = 32
//    var edadProfesor: Int = 32
    var sueldoProfesor = 1.23

    edadProfesor = 23
    sueldoProfesor = 2.33

//    Mutable / Inmutable

//    MUTABLES: Se puede RE asignar "="
    var edadCachorro: Int = 0
    edadCachorro = 1
    edadCachorro = 2
    edadCachorro = 3

//    INMUTABLE: No se puede RE asignar
    val numeroCedula = 1726264961
//    numeroCedula = 141651

//Tipos de variables (JAVA)
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 2.3
    val estadoCivil: Char = 'C'
    val fechaNacimiento: Date = Date()


//    Sentencias If
    if (true) {
//        sentencias verdaderas
    } else {
//        sentencias falsas
    }

//    switch Estado -> S -> C

    val estadoCivilWhen: String = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }


//    If de una sola línea
    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
//    condicion ? bloque-true: bloque false

    imprimirNombre("Andres")

    calcularSueldo(100.00)
    calcularSueldo(100.00, 14.00)
    calcularSueldo(100.00, 14.00, 25.00)


    //Named Parameters
    calcularSueldo(
        bonoEspecial = 15.00,
        sueldo = 150.00
    )
    calcularSueldo(
        tasa = 10.00,
        bonoEspecial = 25.00,
        sueldo = 1500.00
    )

//  Tipos de Arreglos

//    Arreglos Estátidos
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
//    arreglo estátic.add -> NO TENEMOS AQUI, NO SE PUEDE MODIFICAR LOS ELEMENTOS DEL ARREGLO

//    Arreglo Dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)


//    OPERADORES -> Sirven para los arreglos estáticos y dinánicos

//    FOR EACH -> Unit
//  Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach() { valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    println(respuestaForEach)

    arregloDinamico.forEach {
//        it: Int -> El valor o los valores que van a llegar a esta funcion
//        Si solamente se recibe 1 parámetro, este se va a llamar "it"
        println("Valor actual: ${it}")
    }


    arregloDinamico.forEachIndexed { indice: Int, valorActual: Int ->
        println("Valor ${valorActual} Indice ${indice}")
    }


//    MAP -> Muta el arreglo (Cambia el arreglo)
//    1) Enviemos
//    2)Nos devuelve un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map { valorActual: Int ->
        return@map valorActual.toDouble() + 100.00
    }
    println(respuestaMap)

//Se resume lo de arriaba en una linea
    println(arregloDinamico.map { it + 15 })

//Filter


    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            val matoresACinco: Boolean = valorActual > 5        //Expresion Condicion
            return@filter matoresACinco
        }

    println(respuestaFilter)

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilterDos)


//    OR y AND
//    OR -> ANY  (Alguno cumple)
//    AND -> ALL  (Todos cumplen)


    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny)   //true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAny)   //false

//    Reduce -> Devuelve un valor acumulado.
//    1) Devuelve el valor acumulado
//    2) En que valor empieza
//    [1,2,3,4,5]
//    valorIteracion1 = ValorInicial + 1 -> Iteracion 1
//    3 = valorIteracion1 + 2 = 1+ 2 = acumulado -> Iteracion 2
//    6 = valorIteracion2 + 3 = 3 + 3 = acumulado -> Iteracion 3
//    10 = valorIteracion3 + 4 = 6 + 4 = acumulado -> Iteracion 4
//    15 = valorIteracion4 + 5 = 10 + 5 = acumulado -> Iteracion 5
//    -> ÚltimoAcumulado = 15 -> Es la suma de todos los valores del arreglo



    val respuestaReduce: Int = arregloDinamico
        .reduce{//acumulado = 0 -> Siempre empieza en cero
                acumulado: Int, valorActual: Int->
            return@reduce (acumulado + valorActual) //lógica del negocio
        }
    println(respuestaReduce)    //78


//    Empieza con 100 de vida
//    [12, 15, 8, 10]

    val arregloDanio = ArrayList<Int>(12,15,8,10)

    val respuestaReduceFold = arregloDanio
        .fold(
            100,
            {
                    acumulado,valorActualIteracion ->
                return@fold acumulado - valorActualIteracion
            }
        )
    println(respuestaReduceFold)













}   //Fin bloque main


fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //parámetro requerido
    tasa: Double = 12.00,    //parámetro opcional(Defecto)
    bonoEspecial: Double? = null  //parámetro opcional (NULL)
): Double {
    if (bonoEspecial == null) { //Indentar Ctrl + A ->Ctrl + Alt + L
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }

}











