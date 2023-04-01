package movieriew.utilities.cli

open class Banner {

    private val maxWidth = 100

    fun display(title: String, body: String? = null) {

        if (title.isNotEmpty()) {
            displayTitle(title)
        }


        if (!body.isNullOrEmpty()) {
            displayBody(body)
        }
    }


    private fun displayLine(content: String, upperLine: Boolean = false, bottomLine: Boolean = false) {
        if (upperLine) {
            boxTop()
        }

        for (line in wordWrap(content)) {
            boxLeft()
            print(line)
            boxRight(line.length)
        }

        if (bottomLine) {
            boxBottom()
        }
    }

    private fun displayTitle(content: String, upperLine: Boolean = true, bottomLine: Boolean = true) {
        displayLine(content = content, upperLine = upperLine, bottomLine = bottomLine)
    }

    fun displayBody(content: String, bottomLine: Boolean = true) {
        if (content.isNotEmpty()) {

            if (content.contains("\n")) {
                for (line in content.split("\n")) {
                    displayLine(line)
                }
            } else {
                displayLine(content)
            }

            if (bottomLine) {
                boxBottom()
            }
        }
    }

    private fun boxTop() {
        println("-".repeat(maxWidth))
    }

    private fun boxLeft() {
        print("| ")
    }

    private fun boxRight(length: Int) {
        val limit = maxWidth - 4


        if (length < limit) {
            print(" ".repeat(limit - length))
        }

        print(" |\n")
    }

    private fun boxBottom() {
        println("-".repeat(maxWidth))

    }


    private fun wordWrap(text: String): ArrayList<String> {

        val chunkSize = maxWidth - 4
        val chunks = ArrayList<String>()
        var startIndex = 0
        var endIndex = chunkSize

        while (startIndex < text.length) {
            if (endIndex > text.length) {
                endIndex = text.length
            }
            chunks.add(text.substring(startIndex, endIndex).trim())
            startIndex = endIndex
            endIndex += chunkSize
        }

        return chunks
    }

    companion object {

        fun display(title: String, body: String? = null) {
            Banner().display(title = title, body = body)
        }

        /**
        fun line(content: String, upperLine: Boolean = false, bottomLine: Boolean = false) {
            Banner().displayLine(content, upperLine, bottomLine)
        }
        **/

        fun body(content: String, bottomLine: Boolean = true) {
            if (content.isNotEmpty()) {
                Banner().displayBody(content, bottomLine)
            }
        }

    }
}