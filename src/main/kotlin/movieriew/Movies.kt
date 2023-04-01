package movieriew

import movieriew.utilities.Storage
import movieriew.utilities.Tools

class Movies : MoviesInterface {

    private val users: Users = Users()
    private val reviews: Reviews = Reviews()
    override fun getAll(): Map<String, Movie> {

        return Storage.movies.associateBy { it.id }
    }

    override fun get(id: String): Movie {
        return all.entries.firstOrNull { it.value.id == id }?.value ?: emptyMovie()
    }

    override fun getYears(): List<Int> {
        return all.values.map { it.year }.distinct().sorted().toList()
    }

    override fun getGenres(): List<String> {
        return all.values.map { it.genre }.distinct().sorted().toList()

    }

    override fun getByYear(year: Int): Map<String, Movie> {
        return all.filterValues { it.year == year }

    }

    override fun getByGenre(genre: String): Map<String, Movie> {
        return all.filterValues { it.genre == genre }
    }

    override fun find(query: String): Map<String, Movie> {
        return all.filterValues { it.name.contains(query, ignoreCase = true) }
    }

    override fun remove(id: String) {
        Storage.movies.removeAll { it.id == id }
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

        if (movieRatings.toFloat() > 0F) {
            if (Tools.hasDecimal(movieRatings.toFloat())) {
                return String.format("%.1f", movieRatings.toFloat()) + "/5"
            }
            return "${movieRatings.toFloat().toInt()}/5"

        }

        return "(sin rating)"
    }

    fun parseReviews(movie: Movie): String {
        val reviewList = reviews.getByMovie(movie.id).values

        return when (reviewList.size) {
            0 -> "No hay reseñas para esta película"
            else -> {
                val reviewsText = reviewList.map { review ->
                    "\"${review.first.comment}\"\nPor: ${users.get(review.first.user).realName} - Rank: ${review.first.rank} - Publicado: ${
                        Tools.dateFormat(
                            review.first.date
                        )
                    }\n \n"
                }
                "Existen ${reviewList.size} reseñas: \n \n${reviewsText.joinToString("\n")}"
            }
        }
    }

    fun emptyMovie(): Movie {
        return Movie(id = "", name = "", genre = "", year = 0, synopsis = "", director = "")
    }

}