package fo

import fo.utilities.cli.Banner
import t6.User
import t6.UserType

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

        fun logOut()
        {
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