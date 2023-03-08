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

fun main() {
    if (login()){
        home()
    }else {
        main()
    }
}

fun movieDetails(movie: String) {
    when(movie.lowercase()) {
        "1", "el padrino" -> {
            getObjectMovie("El Padrino", review1)
            optionConsole("El Padrino", review1)
        }
        "2", "la dictadura perfecta" -> {
            getObjectMovie("La Dictadura Perfecta", review2)
            optionConsole("La Dictadura Perfecta", review2)
        }
        "3", "lagrimas del sol" -> {
            getObjectMovie("Lagrimas Del Sol", review3)
            optionConsole("Lagrimas Del Sol", review3)
        }
        "4", "los juegos del hambre" -> {
            getObjectMovie("Los Juegos Del Hambre", review4)
            optionConsole("Los Juegos Del Hambre", review4)
        }
        "5", "shrek" -> {
            getObjectMovie("Shrek", review5)
            optionConsole("Shrek", review5)
        }
        "6", "jurassic world" -> {
            getObjectMovie("Jurassic World", review6)
            optionConsole("Jurassic World", review6)
        }
        else -> {
            println("NO SE ENCONTRÓ LA PELÍCULA SELECCIONADA")
            home()
        }
    }
}

fun getObjectMovie(name: String, description: String, qualifyUser: String = "4") {
    MoviePOO(name, description, qualifyUser!!.toInt()).details()
}

fun optionConsole(name: String, description: String) {
    fun qualify() {
        println("AGREGA UNA CALIFICACIÓN DEL 1..5")
        val qualifyUser = readlnOrNull()
        getObjectMovie(name, description, qualifyUser.toString())
        optionConsole(name, description)
    }

    println("    ¿QUE QUIERES HACER?  \n" +
            "|      1 Menú principal     |\n" +
            "|      2 Cerrar Sesión      |\n" +
            "|      3 Calificar Pelicula |")
    val option = readlnOrNull()
    if (option == "1") home() else if (option == "2") main() else qualify()
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
