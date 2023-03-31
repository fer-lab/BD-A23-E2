package utilities

import AppMenu

abstract class GoToEntity {

    constructor()

    protected lateinit var _gotoMenu: AppMenu
    protected lateinit var _gotoAction: () -> Unit


    fun isMenu(): Boolean
    {
        return this::_gotoMenu.isInitialized
    }

    fun isAction(): Boolean
    {
        return this::_gotoAction.isInitialized
    }

    fun getMenu(): AppMenu
    {
        return this._gotoMenu
    }

    fun getAction(): () -> Unit
    {
        return this._gotoAction
    }
}