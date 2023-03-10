package fo

import fo.utilities.cli.Banner

class Auth {

    companion object
    {
        private var currentUser: User = User(0, "", "", "", "")

        fun user(): User
        {
            if (currentUser.isValid())
            {
                return currentUser
            }

            Auth().loginScreen()

            return currentUser
        }

        fun logOut()
        {
            currentUser = User(0, "", "", "", "")
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

            if (user.validPasswd(passwd)) {
                currentUser = user
                loggedIn = true
            } else {
                println("El usuario y/o contraseña es inválido. Intenta de nuevo. \n")
            }
        }

    }

}