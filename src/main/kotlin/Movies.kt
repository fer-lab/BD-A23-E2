import utilities.Storage
import utilities.Tools
import movieRew.Movie
import movieRew.Movies

class Movies: Movies {

    private val users: Users = Users()
    private val reviews: Reviews = Reviews()
    override fun getAll(): Map<String, Movie> {

        return Storage.movies.associateBy { it.id }
    }

    override fun get(id: String): Movie {
        return getAll().entries.firstOrNull{ it.value.id == id } ?.value ?: emptyMovie()
    }

    override fun getYears(): List<Int>
    {
        return getAll().values.map { it.year }.distinct().sorted().toList()
    }

    override fun getGenres(): List<String>
    {
        return getAll().values.map { it.genre }.distinct().sorted().toList()

    }

    override fun getByYear(year: Int): Map<String, Movie> {
        return getAll().filterValues { it.year == year }

    }

    override fun getByGenre(genre: String): Map<String, Movie> {
        return getAll().filterValues { it.genre == genre }
    }

    override fun find(query: String): Map<String, Movie> {
        return getAll().filterValues { it.name.contains(query, ignoreCase = true) }
    }

    override fun remove(id: String) {
        Storage.movies.removeAll{it.id == id}
    }

    fun parseRating(movie: Movie): String {

        val movieRating: (String) -> String = { movieId ->
            Reviews().getByMovie(movieId)
                .values
                .map { it.first.rank }
                .takeIf { it.isNotEmpty() }
                ?.average()
                ?.toString()
                ?: "0.0"
        }

        val movieRatings = movieRating(movie.id)

        if (movieRatings.toFloat() > 0F)
        {
            if (Tools.hasDecimal(movieRatings.toFloat()))
            {
                return String.format("%.1f", movieRatings.toFloat()) + "/5"
            }
            return "${movieRatings.toFloat().toInt()}/5"

        }

        return "(sin rating)"
    }

    fun parseReviews(movie: Movie): String {

        val reviewList = reviews.getByMovie(movie.id)
        var reviewText = ""

        if (reviewList.size == 1)
        {
            reviewText += " \nExiste una reseña: \n \n"
        }
        else if (reviewList.size > 1)
        {
            reviewText += " \nExisten ${reviewList.size} reseñas: \n \n"
        }

        for (review in reviewList)
        {
            reviewText += "\"${review.value.first.comment}\"\nPor: ${users.get(review.value.first.user).realName} - Rank: ${review.value.first.rank} - Publicado: ${Tools.dateFormat(review.value.first.date)}\n \n"
        }

        return reviewText
    }

    fun emptyMovie(): Movie
    {
        return Movie(id = "", name = "", genre = "", year = 0, synopsis = "", director = "")
    }

}