package fo.utilities.cli

open class Banner {

    private val maxWidth = 100

    fun display(title: String, body: String ? = null, clear: Boolean = true)
    {

        if (clear)
        {
            clear()
        }

        if (title.isNotEmpty())
        {
            displayTitle(title)
        }



        if (body != null)
        {
            displayBody(body)
        }
    }



    private fun displayLine(content: String, upperLine: Boolean = false, bottomLine: Boolean = false)
    {
        if (upperLine)
        {
            boxTop()
        }

        for (line in wordWrap(content))
        {
            boxLeft()
            print(line)
            boxRight(line.length)
        }

        if (bottomLine)
        {
            boxBottom()
        }
    }

    private fun displayTitle(content: String)
    {
        displayLine(content= content, upperLine = true, bottomLine = true)
    }

    fun displayBody(content: String)
    {
        if (content.contains("\n"))
        {
            for(line in content.split("\n"))
            {
                displayLine(line)
            }
        }
        else
        {
            displayLine(content)
        }

        boxBottom()
    }

    private fun boxTop()
    {
        println("-".repeat(maxWidth))
    }

    private fun boxLeft()
    {
        print("| ")
    }

    private fun boxRight(length: Int)
    {
        val limit = maxWidth - 4


        if (length < limit)
        {
            print(" ".repeat(limit - length))
        }

        print(" |\n")
    }

    private fun boxBottom()
    {
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
    companion object
    {

        fun display(title: String, body: String? = null)
        {
            Banner().display(title = title, body)
        }

    }

    fun clear()
    {

    }
}