package fo.utilities.cli

class OptionsResponse(private val response: String, private val options: ArrayList<Option>) {
    fun isValid(): Boolean
    {
        for(option in options)
        {
            if (option.key.lowercase().trim() == get())
            {
                return true
            }
        }

        return false
    }

    fun get(): String
    {
        return response.lowercase().trim()
    }

    override fun toString(): String {
        return get()
    }
}