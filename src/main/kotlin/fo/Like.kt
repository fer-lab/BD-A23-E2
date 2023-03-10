package fo

data class Like(
    val id: String,
    val date: Long,
    val user: Int,
    val movie: Int
)
{
    fun isValid(): Boolean
    {
        return id != ""
    }

    fun remove() {
        Likes().remove(user, movie)
    }
}
