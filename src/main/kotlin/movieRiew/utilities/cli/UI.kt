package movieRiew.utilities.cli

import movieRiew.utilities.GoToEntity

class UI(private var title: String = "") {

    private lateinit var _response: OptionsResponse
    private var bodyList: MutableList<String> = mutableListOf()
    private var actions: Options = Options("")
    private var actionsDefault: UIDefaultActions = UIDefaultActions()

    fun setTitle(title: String): UI
    {
        this.title = title
        return this
    }

    fun addBody(body: String): UI
    {
        this.bodyList.add(body)

        return this
    }

    fun actions(): Options
    {
        return actions
    }


    fun defaultActions(): UIDefaultActions
    {
        return actionsDefault
    }

    fun response(): OptionsResponse
    {

        if (this::_response.isInitialized)
        {
            return _response
        }

        val options = Options(this.actions.getDescription(), this.actions.getKeyMode())
        options.hideHeader = true

        for (option in this.actions.getOptions())
        {
            options.add(option.label, option.key)
        }

        if (!actionsDefault.isDisabled())
        {
            actionsDefault.add("Volver", "V")
            actionsDefault.add("Menú Principal", "M")
            actionsDefault.add("Cerrar Sesión", "X")
        }

        if (this.actionsDefault.size() > 0)
        {
            options.addDivisor()

            for (option in this.actionsDefault.getOptions())
            {
                options.add(option.label, option.key)
            }
        }



        if (title.isNotEmpty())
        {
            Banner.display(title)
        }


        for (body in bodyList) {
            Banner.body(body)
        }

        this._response = options.get(true)

        actionsDefault.defaultRoute(this._response)

        return this._response
    }

    fun defaultRoute(): Boolean
    {
        return actionsDefault.defaultRoute(response())
    }

    fun setBack(back: GoToEntity): UI
    {
        actionsDefault.setBack(back)
        return this
    }

    fun setActionDescription(description: String): UI
    {
        actions().setDescription(description)
        return this
    }

}