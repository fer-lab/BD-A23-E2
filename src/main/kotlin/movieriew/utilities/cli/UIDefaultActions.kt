package movieriew.utilities.cli

import movieriew.App
import movieriew.AppMenu
import movieriew.utilities.GoToEntity
import movieriew.utilities.GoToMenu

class UIDefaultActions : Options("", OptionsKeyTypes.NUMBER) {
    private lateinit var _goto: GoToEntity
    private var disableDefault: Boolean = false
    fun setDisable(): UIDefaultActions {
        disableDefault = true
        return this
    }

    fun isDisabled(): Boolean {
        return disableDefault
    }

    fun setBack(back: GoToEntity) {
        _goto = back
    }

    fun defaultRoute(response: OptionsResponse): Boolean {
        val app = App()


        if (getOptions().any { it.key.trim().toLowerCase() == "v" } && response.get() == "v") {
            if (this::_goto.isInitialized) {

                app.goto(_goto)
            }
            return true
        } else if (getOptions().any { it.key.trim().toLowerCase() == "m" } && response.get() == "m") {
            app.goto(GoToMenu(AppMenu.HOME))
            return true
        } else if (getOptions().any { it.key.trim().toLowerCase() == "x" } && response.get() == "x") {
            app.goto(GoToMenu(AppMenu.LOGOUT))
            return true
        }

        return false

    }


}