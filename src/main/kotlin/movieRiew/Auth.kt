package movieRiew

import kotlinx.coroutines.*
import movieRiew.utilities.cli.Banner

class Auth {

    companion object
    {
        private var currentUser: User = User("", "", "", UserType.USER, "")

        fun user(): User
        {
            if (currentUser.id.isNotEmpty())
            {
                return currentUser
            }

            Auth().loginScreen()

            return currentUser
        }

        suspend fun logOut() = withContext(Dispatchers.IO) {
            val job = launch {
                delay(2000L)
                println("Sesión Finalizada...\n\n\n")
                delay(1000L)
            }
            println("Cerrando sesión...")
            job.join()
            currentUser = User("", "", "", UserType.USER, "")
            App().run()
        }

        fun logOutx()
        {



            runBlocking {

                launch {
                    delay(2000L)
                    println("Sesión FInalizada...\n\n\n")
                }
                println("Cerrando sesión...")

            }

            currentUser = User("", "", "", UserType.USER, "")
            App().run()
        }

    }





    private fun loginScreen()
    {
        if (App.devMode())
        {
            currentUser = Users().getByUserName("user1")
            return
        }


        var loggedIn = false

        Banner.display("Inicio de Sesión", "Ingresa tu usuario y contraseña.")


        while (!loggedIn) {
            print("usuario: ")
            val userName = readln().trim().lowercase()
            print("contraseña: ")
            val passwd = readln().trim().lowercase()

            val user = Users().getByUserName(userName)

            if (user.id.isNotEmpty() && user.userPasswd == passwd) {
                currentUser = user
                loggedIn = true
            } else {
                println("El usuario y/o contraseña es inválido. Intenta de nuevo. \n")
            }
        }

    }

}