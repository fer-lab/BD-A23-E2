package movieRew


data class Movie(
    val id: String,
    val name: String,
    val genre: String,
    val year: Int,
    val synopsis: String,
    val director: String
) {
}