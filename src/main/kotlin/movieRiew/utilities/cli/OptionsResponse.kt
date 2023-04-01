package movieRiew.utilities.cli

class OptionsResponse(private val response: String, private val options: ArrayList<Option>) {
    fun isValid(): Boolean {
        for (option in options) {
            if (option.key.toLowerCase().trim() == get()) {
                return true
            }
        }

        return false
    }

    fun get(): String {
        return response.toLowerCase().trim()
    }

    override fun toString(): String {
        return get()
    }

    fun toInt(): Int {
        return if (get().toIntOrNull() == null) 0 else get().toInt()
    }
}