package movieriew.utilities.cli

open class Options(private var description: String, private val mode: OptionsKeyTypes = OptionsKeyTypes.NUMBER) {

    private val options: ArrayList<Option> = ArrayList<Option>()
    private val dictionary: HashMap<Int, Char> = createDictionary()
    var hideHeader: Boolean = false

    fun setDescription(description: String): Options {
        this.description = description
        return this
    }


    fun add(label: String, key: String? = null): Options {
        options.add(Option(key = key ?: nextKey(), label = label))
        return this
    }

    fun addDivisor(): Options {
        return add("__divisor__", "99999")

    }

    fun get(tryAgain: Boolean = false, warning: String? = null): OptionsResponse {
        displayOptions()

        var response = OptionsResponse(getResponse(), options)

        val displayWarning = fun(warning: String) {
            if (warning != "") {
                println("*** $warning ***")
            } else {
                println("*** Opción Inválida ***")
            }
        }

        if (!response.isValid() && tryAgain) {

            while (true) {
                displayWarning(warning.orEmpty())
                response = OptionsResponse(getResponse(), options)
                if (response.isValid()) {
                    return response
                }
            }

        } else if (!response.isValid()) {
            displayWarning(warning.orEmpty())
        }

        return response
    }

    private fun displayOptions() {
        var body = ""

        if (hideHeader && description.isNotEmpty()) {
            body += description + "\n  \n "
        }

        if (options.size > 0) {
            for (option in options) {
                body += if (option.label == "__divisor__") "  \n" else "${option.key}. ${option.label}\n"
            }
        } else {
            body = "No existen opciones"
        }

        if (hideHeader) {
            Banner.display("", body.trim())
        } else {
            Banner.display(this.description, body.trim())
        }
    }

    private fun getResponse(): String {
        print(": ")
        return (readLine() as String).toLowerCase().trim()
    }


    fun nextKey(): String {
        val nextItem: Int = (options.size + 1)

        return if (isModeNumber()) {
            nextItem.toString()
        } else {

            if (dictionary.containsKey(nextItem)) {
                dictionary[nextItem].toString()
            } else {
                "??"
            }

        }
    }


    private fun isModeNumber(): Boolean {
        return !isModeLetter()
    }

    private fun isModeLetter(): Boolean {

        return when (mode) {
            OptionsKeyTypes.LETTER -> true
            else -> false
        }
    }

    private fun createDictionary(): HashMap<Int, Char> {
        var value: Char
        var key: Int
        val map = HashMap<Int, Char>()

        value = 'a'
        key = 1

        while (value <= 'z') {
            map[key] = value
            key++
            ++value
        }

        return map
    }

    fun size(): Int {
        return options.size
    }

    fun getOptions(): ArrayList<Option> {
        return options
    }

    fun getDescription(): String {
        return description
    }

    fun getKeyMode(): OptionsKeyTypes {
        return mode
    }
}