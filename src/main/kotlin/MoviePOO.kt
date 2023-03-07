class MoviePOO(val name: String, val description: String, var qualify: Int = 1) {
    fun details() {
        println("**************************************  $name  **************************************")
        val qualify = when (qualify) {
            1 -> "*"
            2 -> "**"
            3 -> "***"
            4 -> "****"
            5 -> "*****"
            else -> "Esta calificación se encuentra fuera del rango 1-5"
        }
        println("-----> Calificación: |$qualify|")
        println(description)
    }
}