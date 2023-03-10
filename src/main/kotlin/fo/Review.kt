package fo

data class Review(
    val id: String,
    val date: Long,
    val user: Int,
    val movie: Int,
    val rank: Int,
    val comment: String


)
{
    fun isValid(): Boolean
    {
        return id != ""
    }

    fun remove() {
        Reviews().remove(user, movie)
    }
}