package movieRew

data class Review(
    val id: String,
    val user: String,
    val movie: String,
    val rank: Int,
    val comment: String,
    val date: Long
) {
}