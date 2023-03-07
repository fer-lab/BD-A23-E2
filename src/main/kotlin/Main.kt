import kotlin.math.log

const val review1 = "Michael Corleone, héroe de guerra e hijo menor de un poderoso jefe de la mafia \n" +
                    "neoyorquina, decide unirse a la organización cuando su padre sufre un atentado."
const val review2 = "Tras aceptar un soborno descomunal, una poderosa corporación televisiva \n" +
                    "intenta mejorar con mentiras la imagen de un notorio político corrupto."
const val review3 = "Cuando la guerra asola Nigeria, un miembro del equipo SEAL de la Marina \n" +
                    "deberá rescatar a una médica refugiada en la jungla y a toda la gente bajo \n" +
                    "su cuidado"
const val review4 = "En un mundo post-apocalíptico, Katniss y Peeta representan a su distrito en una \n" +
                    "competencia televisada en la que cada participante lucha contra la muerte."
const val review5 = "En una misión para recuperar a una hermosa princesa de las garras de un dragón feroz, \n" +
                    "un ogro gruñón forma un equipo con un burro ocurrente."
const val review6 = "Un parque temático de dinosaurios busca atraer turistas con una emocionante \n" +
                    "exhibición, pero su criatura más terrible logra escapar y desata el caos en la isla."

fun main(args: Array<String>) {
    if (login()){
        home()
    }else {
        login()
    }
}

fun movieDetails(movie: String) {
    when(movie.lowercase()) {
        "1", "el padrino" -> {
            MoviePOO("El Padrino", review1).details()
            qualifyMovie("El Padrino", review1)
        }
        "2", "la dictadura perfecta" -> {
            MoviePOO("La Dictadura Perfecta", review2).details()
            qualifyMovie("La Dictadura Perfecta", review2)
        }
        "3", "lagrimas del sol" -> {
            MoviePOO("Lagrimas Del Sol", review3).details()
            qualifyMovie("Lagrimas Del Sol", review3)
        }
        "4", "los juegos del hambre" -> {
            MoviePOO("Los Juegos Del Hambre", review4).details()
            qualifyMovie("Los Juegos Del Hambre", review4)
        }
        "5", "shrek" -> {
            MoviePOO("Shrek", review5).details()
            qualifyMovie("Shrek", review5)
        }
        "6", "jurassic world" -> {
            MoviePOO("Jurassic World", review6).details()
            qualifyMovie("Jurassic World", review6)
        }
        else -> {
            println("NO SE ENCONTRÓ LA PELÍCULA SELECCIONADA")
            home()
        }
    }
}

fun qualifyMovie(name: String, description: String) {
    fun qualify() {
        println("AGREGA UNA CALIFICACIÓN DEL 1-5")
        val qualifyUser = readlnOrNull()
        MoviePOO(name, description, qualifyUser!!.toInt()).details()
        qualifyMovie(name, description)
    }

    println("-------------------------¿QUE QUIERES HACER?  |1 Menú principal|  |2 Cerrar Sesión|  |3 Calificar Pelicula|-------------------------")
    val option = readlnOrNull()
    if (option == "1") home() else if (option == "2") login() else qualify()
}

fun login(): Boolean{
    println("Bienvenido Usuario!")
    println("*******Login*******")
    println("INGRESE EL USUARIO")
    val user = readlnOrNull()
    println("INGRESE LA CONTRASEÑA")
    val pass = readlnOrNull()

    return if(user == "1234" && pass == "1234"){
        println("*******************¡Inicio de sesión exitoso!*******************")
        true
    } else{
        println("*******************¡Usuario o contraseña incorrecto! VUELVA A INTENTAR*******************")
        false
    }
}

fun home() {
    println("----------------------TODAS LAS PELÍCULAS----------------------")
    println("________________________________")
    val peliculas = setOf(
        "| 1 El Padrino                 |",
        "| 2 La Dictadura Perfecta      |",
        "| 3 Lagrimas Del Sol           |",
        "| 4 Los Juegos Del Hambre      |",
        "| 5 Shrek                      |",
        "| 6 Jurassic World             |"
    )
    peliculas.forEach{ println(it) }
    println("--------------------------------")
    println("INGRESE EL NOMBRE DE LA PELÍCULA O NÚMERO PARA VER SUS DETALLES")
    movieDetails(readlnOrNull().toString())
}
