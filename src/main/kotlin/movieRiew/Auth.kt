package movieRiew

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import movieRiew.utilities.GoToAction
import movieRiew.utilities.Storage
import movieRiew.utilities.Tools
import movieRiew.utilities.cli.Banner
import movieRiew.utilities.cli.UI
import kotlin.system.exitProcess

class Auth {

    companion object {
        private var currentUser: User = User("", "", "", UserType.USER, "")

        fun user(): User {
            if (currentUser.id.isNotEmpty()) {
                return currentUser
            }

            Auth().loginScreen()

            return currentUser
        }

        suspend fun logOut() = withContext(Dispatchers.IO) {
            val job = launch {
                delay(2000L)
                println("Sesión Finalizada...")
                delay(1000L)
                Tools.newPage()
            }
            println("Cerrando sesión...")
            job.join()
            currentUser = User("", "", "", UserType.USER, "")
            App().run()
        }

    }


    private fun loginScreen() {
        if (App.devMode()) {
            currentUser = Users().getByUserName("user1")
            return
        }


        val ui = UI("Bienvenido a MovieRew")
        ui.defaultActions().setDisable()
        ui.setActionDescription("¿Qué quieres hacer?")
        ui.addAction("Iniciar Sesión")
        ui.addAction("Crear Cuenta")
        ui.addDivisor()
        ui.addAction("Salir", "x")

        when (ui.response().get()) {
            "1" -> GoToAction.action({ loginPrompt() })
            "2" -> GoToAction.action({
                newAccount()
                loginScreen()
            })

            "x" -> exitProcess(0)
        }

    }


    private fun loginPrompt() {

        Banner.display("Inicio de Sesión", "Ingresa tu usuario y contraseña.")

        print("usuario: ")
        val userName = (readLine() as String).trim().toLowerCase()
        print("contraseña: ")
        val passwd = (readLine() as String).trim().toLowerCase()

        val user = Users().getByUserName(userName)

        if (user.id.isNotEmpty() && user.userPasswd == passwd) {
            currentUser = user
            return
        } else {

            UI.displayError("El usuario y/o contraseña es inválido...", 2)
            loginScreen()

        }

    }

    private fun newAccount() {
        Banner.display("Crear Cuenta", "Ingresa tu nombre, nombre de usuario y contraseña.")

        print("nombre: ")
        val realName = (readLine() as String).trim().toLowerCase()

        if (realName.isEmpty()) {
            UI.displayError("El nombre es inválido", 1)
            return
        }

        print("usuario: ")
        val userName = (readLine() as String).trim().toLowerCase()

        if (userName.isEmpty()) {
            UI.displayError("El usuario es inválido", 1)
            return
        } else if (Users().getByUserName(userName).id.isNotEmpty()) {
            UI.displayError("El usuario ya existe", 1)
            return
        }

        print("contraseña: ")
        val passwd = (readLine() as String).trim().toLowerCase()
        if (passwd.isEmpty()) {
            UI.displayError("La contraseña es inválida", 2)
            return
        }

        Storage.users.add(
            User(
                id = Tools.uuid(),
                userName = userName,
                realName = realName,
                userPasswd = passwd,
                userType = UserType.USER
            )
        )

        println("\n\nUsuario creado!\n\n")
        GoToAction.setDelay(2, "")

    }

}