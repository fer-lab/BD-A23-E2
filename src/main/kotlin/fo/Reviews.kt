package fo

import fo.utilities.Storage
import java.util.*

class Reviews {

    private fun getAll(): Map<String, Review> {

        return Storage.reviews.associateBy { it.id }
    }

    fun get(id: String): Review {

        val review = getAll().values.firstOrNull{ it.id == id }

        if (review != null) {
            return review
        }

        return emptyReview()
    }

    fun userReview(userId: Int, movieId: Int): Review
    {
        val review = userReviews(userId).values.firstOrNull { it.movie == movieId }

        if (review != null)
        {
            return review
        }

        return emptyReview()
    }

    fun userReviews(userId: Int): Map<String, Review>
    {
        return getAll().values.filter { it.user == userId }.associateBy { it.id }
    }

    fun movieReviews(movieId: Int): Map<String, Review>
    {
        return getAll().values.filter { it.movie == movieId }.associateBy { it.id }
    }

    fun add(movieId: Int, userId: Int, rank: Int, comment: String, date: Long = System.currentTimeMillis())
    {
        val newReview = Review(id = UUID.randomUUID().toString(), date, user=userId, movie=movieId, rank, comment)
        val user = Users().get(userId)
        val movie = Movies().get(movieId)

        if (!user.isValid())
        {
            throw Exception("El usuario no existe")
        }

        if (!movie.isValid())
        {
            throw Exception("La película no existe")
        }

        if (Storage.reviews.none { it.user == newReview.user && it.movie == newReview.movie }) {
            Storage.reviews.add(newReview)
        }
        else {
            throw Exception("Ya existe una reseña del usuario ${user.userName} para la película ${movie.name}")
        }

    }

    fun remove(userId: Int, movieId: Int)
    {
        val review = userReview(userId, movieId)

        if (review.isValid())
        {
            Storage.reviews.removeAll{it.id == review.id}
        }

    }

    private fun emptyReview(): Review
    {
        return Review("", 0L, 0, 0, 0, "")
    }
}