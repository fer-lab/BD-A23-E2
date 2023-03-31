package movieRew

interface Movies {
    fun get(id: String): Movie

    fun getAll(): Map<String, Movie>

    fun getByYear(year: Int): Map<String, Movie>

    fun getByGenre(genre: String): Map<String, Movie>

    fun find(query: String): Map<String, Movie>

    fun getYears(): List<Int>

    fun getGenres(): List<String>

    fun remove(id: String)
}