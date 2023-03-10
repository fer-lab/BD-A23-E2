package fo.utilities.cli

class Action: Banner() {

    fun display(title: String, action: Options, body: String ? = null, footer: String ? = null, clear: Boolean = true): OptionsResponse
    {
        super.display(title, body, clear)

        if (!footer.isNullOrEmpty())
        {
            displayBody(footer)
        }

        action.hideHeader = true
        return getResponse(action)

    }

    private fun getResponse(actions: Options): OptionsResponse
    {
        return actions.get(true)
    }
    companion object
    {
        fun display(title: String, action: Options, body: String ? = null, footer: String ? = null, clear: Boolean = true): OptionsResponse
        {
            return Action().display(title = title, action = action, body = body, footer = footer, clear = clear)

        }
    }
}