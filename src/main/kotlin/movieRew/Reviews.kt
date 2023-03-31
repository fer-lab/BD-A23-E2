package movieRew

import movieRew.utilities.Storage
import movieRew.utilities.Tools
import java.util.*

class Reviews: ReviewsInterface {


    override fun getAll(): Map<String, Pair<Review, Movie>> {

        val list: MutableMap<String, Pair<Review, Movie>> = mutableMapOf()
        for(review in Storage.reviews)
        {
            list[review.id] = Pair(review, Movies().get(review.movie))
        }

        return list

    }

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


    override fun add(userId: String, movieId: String, rank: Int, comment: String, date: Long ?): Review {

        val newReview = Review(id = UUID.randomUUID().toString(), userId, movieId, rank, comment, date ?: Tools.currentDate())
        val user = Users().get(userId)
        val movie = Movies().get(movieId)


        if (user.id.isEmpty())
        {
            throw Exception("El usuario no existe")
        }

        if (movie.id.isEmpty())
        {
            throw Exception("La película no existe")
        }

        if (Storage.reviews.none { it.user == newReview.user && it.movie == newReview.movie }) {
            Storage.reviews.add(newReview)
        }
        else {
            throw Exception("Ya existe una reseña del usuario ${user.userName} para la película ${movie.name}")
        }

        return newReview

    }

    override fun remove(id: String)
    {

        if (id.isNotEmpty())
        {
            Storage.reviews.removeAll{it.id == id}
        }

    }

    private fun emptyReview(): Review
    {
        return Review("", "", "", 0, "", 0L)
    }

}