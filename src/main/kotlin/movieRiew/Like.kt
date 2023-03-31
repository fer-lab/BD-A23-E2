package movieRiew

data class Like(
    val id: String,
    val user: String,
    val movie: String,
    val date: Long,
)