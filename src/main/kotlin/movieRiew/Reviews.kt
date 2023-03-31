package movieRiew

import movieRiew.utilities.Storage
import movieRiew.utilities.Tools
import java.util.*

class Reviews: ReviewsInterface {


    override fun getAll(): Map<String, Pair<Review, Movie>> = Storage.reviews.associate { it.id to (it to Movies().get(it.movie)) }


    override fun get(id: String): Review {

        return getAll().values.firstOrNull{ it.first.id == id }?.first ?: emptyReview()
    }

    override fun getByUserAndMovie(userId: String, movieId: String): Review
    {
        return getAll().values.firstOrNull{ it.first.user == userId && it.first.movie == movieId }?.first ?: emptyReview()
    }

    override fun getByUser(userId: String): Map<String, Pair<Review, Movie>>
    {
        return getAll().filterValues { it.first.user == userId }
    }

    override fun getByMovie(movieId: String): Map<String, Pair<Review, Movie>>
    {
        return getAll().filterValues { it.first.movie == movieId }
    }


    override fun add(userId: String, movieId: String, rank: Int, comment: String, date: Long?): Review =
        run {
            val user = Users().get(userId)
            val movie = Movies().get(movieId)

            require(user.id.isNotEmpty()) { "El usuario no existe" }
            require(movie.id.isNotEmpty()) { "La película no existe" }
            require(Storage.reviews.none { it.user == userId && it.movie == movieId }) {
                "Ya existe una reseña del usuario ${user.userName} para la película ${movie.name}"
            }

            Review(
                id = UUID.randomUUID().toString(),
                user = userId,
                movie = movieId,
                rank = rank,
                comment = comment,
                date = date ?: Tools.currentDate()
            ).also { Storage.reviews.add(it) }
        }


    override fun remove(id: String)
    {
        Storage.reviews.removeAll { it.id == id }

    }

    private fun emptyReview(): Review = Review(id = "", movie = "", user = "", rank = 0, comment = "", date = 0L)

}