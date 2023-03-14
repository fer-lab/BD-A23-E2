package fo.utilities.cli

import fo.App
import fo.AppMenu
import fo.utilities.GoToEntity
import fo.utilities.GoToMenu

class UIDefaultActions: Options("", OptionsKeyTypes.NUMBER) {
    private lateinit var _goto: GoToEntity
    private var disableDefault: Boolean = false
    fun setDisable(): UIDefaultActions
    {
        disableDefault = true
        return this
    }

    fun isDisabled(): Boolean
    {
        return disableDefault
    }

    fun setBack(back: GoToEntity)
    {
        _goto = back
    }

     fun defaultRoute(response: OptionsResponse): Boolean
     {
         val app = App()


         if (getOptions().any{ it.key.trim().lowercase() == "v"} && response.get() == "v")
         {
             if (this::_goto.isInitialized) {

                 app.goto(_goto)
             }
             return true
         }
         else if (getOptions().any{ it.key.trim().lowercase()  == "m"} && response.get() == "m")
         {
             app.goto(GoToMenu(AppMenu.HOME))
             return true
         }
         else if (getOptions().any{ it.key.trim().lowercase()  == "x"} && response.get() == "x")
         {
             app.goto(GoToMenu(AppMenu.LOGOUT))
             return true
         }

         return false

     }


}