package fo


data class Movie(
    val id: Int,
    val name: String,
    val genre: String,
    val year: Int,
    val synopsis: String,
    val director: String
)
{

    fun isValid(): Boolean
    {
        return id > 0
    }
    fun remove()
    {
        Movies().remove(id)
    }

    fun ratings(): String {

        var score = 0
        var totalReviews = 0
        var finalScore = 0F

        for(review in Reviews().movieReviews(id))
        {
            totalReviews++
            score += review.value.rank
        }

        if (score > 0 && totalReviews > 0) {

            finalScore = score.toFloat() / totalReviews.toFloat()
        }

        return finalScore.toString()
    }



}