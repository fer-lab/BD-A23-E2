package fo

import fo.utilities.Storage
import fo.utilities.Tools

class Movies {

    private val users: Users = Users()
    fun getAll(): Map<Int, Movie> {

        return Storage.movies.associateBy { it.id }
    }

    fun get(id: Int): Movie {
        val movie = getAll().values.firstOrNull{ it.id == id }

        if (movie != null) {
            return movie
        }

        return Movie(id = 0, name = "", genre = "", year = 0, synopsis = "", director = "")
    }

    fun getYears(): Map<Int,Int>
    {
        return getAll().values.groupBy { it.year }
            .toSortedMap()
            .keys
            .mapIndexed { index, year -> index + 1 to year }
            .toMap()
    }

    fun getGenres(): Map<Int,String>
    {
        return getAll().values.groupBy { it.genre }
            .keys
            .sorted()
            .mapIndexed { index, genre -> index + 1 to genre }
            .toMap()
    }

    fun byYear(year: Int): Map<Int, Movie> {
        return getAll().filterValues { it.year == year }

    }

    fun byGenre(genre: String): Map<Int, Movie> {
        return getAll().filterValues { it.genre == genre }
    }

    fun find(query: String): Map<Int, Movie> {
        return getAll().filterValues { it.name.contains(query, ignoreCase = true) }
    }

    fun remove(id: Int) {
        Storage.movies.removeAll{it.id == id}
    }

    fun parseRating(movie: Movie): String {

        val movieRatings = movie.ratings()

        if (movieRatings.toFloat() > 0)
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

        val reviewList = Reviews().movieReviews(movie.id)
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
            reviewText += "\"${review.value.comment}\" por ${users.get(review.value.user).realName} (${review.value.rank}).\n \n"
        }

        return reviewText
    }

}